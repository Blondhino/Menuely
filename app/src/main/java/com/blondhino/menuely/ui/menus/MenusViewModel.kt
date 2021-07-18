package com.blondhino.menuely.ui.menus

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.CreateMenuModel
import com.blondhino.menuely.data.common.model.MenuModel
import com.blondhino.menuely.data.common.model.RestaurantModel
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
    val createMenuModel: CreateMenuModel = CreateMenuModel()
    val menus: MutableState<List<MenuModel>> = mutableStateOf(ArrayList())
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

    private fun refreshMenus()= viewModelScope.launch {
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
        isLoading.value=true
        val response = repo.createRestaurantMenu(createMenuModel.provideMenuModel())
        if(response.status==Status.SUCCESS){
            refreshMenus()
        }else{
            isLoading.value=false
        }
    }

}