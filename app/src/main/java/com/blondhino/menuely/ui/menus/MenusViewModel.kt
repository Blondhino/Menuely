package com.blondhino.menuely.ui.menus

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.*
import com.blondhino.menuely.data.common.response.MenuCategoryResponse
import com.blondhino.menuely.data.database.dao.RestaurantDao
import com.blondhino.menuely.data.repo.MenusRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenusViewModel @Inject constructor(
    private val menuelyApi: MenuelyApi,
    private val repo: MenusRepo,
    private val restaurantDao: RestaurantDao
) :
    ViewModel() {
    private val initialFetchDone = mutableStateOf(false)
    private val initialCategoryFetchDone = mutableStateOf(false)
    private var lastSelectedMenuId = 0
    val createMenuModel: CreateMenuModel = CreateMenuModel()
    val createCategoryModel: CreateCategoryModel = CreateCategoryModel()
    val menus: MutableState<List<MenuModel>> = mutableStateOf(ArrayList())
    val categories: MutableState<List<MenuCategoryResponse>> = mutableStateOf(ArrayList())
    val selectedMenu: MutableState<MenuModel> = mutableStateOf(MenuModel())
    val isLoading = mutableStateOf(false)


    fun fetchMenus() {
        if (!initialFetchDone.value) {
            isLoading.value = true
            viewModelScope.launch {
                val response =
                    restaurantDao.getRestaurant()?.id?.let { repo.getRestaurantMenus(it) }
                if (response?.status == Status.SUCCESS) {
                    response.data?.let {
                        menus.value = it
                    }
                }
                isLoading.value = false;

            }
        }
        initialFetchDone.value = true
    }

    private fun refreshMenus() = viewModelScope.launch {
        isLoading.value = true
        val response =
            restaurantDao.getRestaurant()?.id?.let { repo.getRestaurantMenus(it) }
        if (response?.status == Status.SUCCESS) {
            response.data?.let {
                menus.value = it
            }
            isLoading.value = false
        }
    }

    fun createMenu() = viewModelScope.launch {
        isLoading.value = true
        val response = repo.createRestaurantMenu(createMenuModel.provideMenuModel())
        if (response.status == Status.SUCCESS) {
            refreshMenus()
        } else {
            isLoading.value = false
        }
    }

    fun createCategory() = viewModelScope.launch {
        isLoading.value = true
        val response =
            createCategoryModel.provideCategoryModel()?.let { repo.createMenuCategory(it) }
        if (response.status == Status.SUCCESS) {
            refreshCategory()
        } else {
            isLoading.value = false
        }

    }

    fun fetchCategory() {
        Log.d("fetchCategory", "called")
        Log.d(
            "fetchCategory",
            "initialFetchDonde : ${initialCategoryFetchDone.value} lastSelectedMenuId: $lastSelectedMenuId selected menu id: ${selectedMenu.value.id}"
        )
        //if (!initialCategoryFetchDone.value && lastSelectedMenuId != selectedMenu.value.id) {
        if ( lastSelectedMenuId != selectedMenu.value.id) {
            viewModelScope.launch {
                isLoading.value = true
                val response = selectedMenu.value.id?.let { repo.getCategoriesForMenu(it) }
                response?.data?.let {
                    categories.value = it
                }
                isLoading.value = false;
                initialCategoryFetchDone.value = true
                selectedMenu.value.id?.let {
                    lastSelectedMenuId = it
                }
            }
        }
    }

    private fun refreshCategory() {
        viewModelScope.launch {
            isLoading.value = true
            val response = selectedMenu.value.id?.let { repo.getCategoriesForMenu(it) }
            response?.data?.let {
                categories.value = it
            }
            isLoading.value = false;
        }
    }

}