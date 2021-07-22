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
import com.blondhino.menuely.data.common.response.MenuProductsResponse
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
    private var lastSelectedCategoryId = 0
    val createMenuModel: CreateMenuModel = CreateMenuModel()
    val createCategoryModel: CreateCategoryModel = CreateCategoryModel()
    val createProductModel: CreateProductModel = CreateProductModel()
    val menus: MutableState<List<MenuModel>> = mutableStateOf(ArrayList())
    val categories: MutableState<List<MenuCategoryResponse>> = mutableStateOf(ArrayList())
    val products: MutableState<List<MenuProductsResponse>> = mutableStateOf(ArrayList())
    val selectedMenu: MutableState<MenuModel> = mutableStateOf(MenuModel())
    val selectedCategory: MutableState<MenuCategoryResponse?> = mutableStateOf(null)
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
                selectedMenu.value.name = createMenuModel.name.value
                selectedMenu.value.currency = createMenuModel.currency.value
                selectedMenu.value.description = createMenuModel.description.value
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
            refreshCategories()
        } else {
            isLoading.value = false
        }

    }

    fun updateMenuCategory() = viewModelScope.launch {
        isLoading.value = true
        val response = selectedCategory.value?.id?.let {
            repo.updateMenuCategory(
                createCategoryModel.provideCategoryModel(),
                it
            )
        }
        if (response?.status == Status.SUCCESS) {
            isLoading.value = false
            refreshCategories()
        } else {
            isLoading.value = false
        }
    }


    fun createProduct() = viewModelScope.launch {
        selectedCategory.value?.id?.let {
            createProductModel.categoryId.value = it
        }
        isLoading.value = true
        val response = repo.createProduct(createProductModel.provideProductModel())
        if (response.status == Status.SUCCESS) {
            refreshProducts()
        } else {
            isLoading.value = false
        }
    }

    fun fetchProducts() {
        if (lastSelectedCategoryId != selectedCategory.value?.id) {
            viewModelScope.launch {
                products.value = products.value.drop(products.value.size)
                isLoading.value = true
                val response = selectedCategory.value?.id?.let { repo.getProductsForMenu(it) }
                if (response?.status == Status.SUCCESS) {
                    response.data?.let {
                        products.value = it
                    }
                }
                isLoading.value = false
                selectedCategory.value?.id?.let { lastSelectedCategoryId = it }
            }
        }
    }

    private fun refreshProducts() {
        viewModelScope.launch {
            isLoading.value = true
            val response = selectedCategory.value?.id?.let { repo.getProductsForMenu(it) }
            if (response?.status == Status.SUCCESS) {
                response.data?.let {
                    products.value = it
                }
            }
            isLoading.value = false
        }
    }

    fun fetchCategory() {
        if (lastSelectedMenuId != selectedMenu.value.id) {
            viewModelScope.launch {
                categories.value = categories.value.drop(categories.value.size)
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

    private fun refreshCategories() {
        viewModelScope.launch {
            isLoading.value = true
            val response = selectedMenu.value.id?.let { repo.getCategoriesForMenu(it) }
            response?.data?.let {
                try {
                    val updated = it.find { category -> category.id == selectedCategory.value?.id }
                    selectedCategory.value?.image?.url = updated?.image?.url
                } catch (e: Exception) {
                }
                categories.value = it
            }
            isLoading.value = false;
        }
    }


    fun deleteMenu() = viewModelScope.launch {
        isLoading.value = true
        val response = selectedMenu.value.id?.let { repo.deleteRestaurantMenu(it) }
        if (response?.status == Status.SUCCESS) {
            isLoading.value = false
            refreshMenus()
        } else {
            isLoading.value = false
        }
    }

    fun deleteMenuCategory() = viewModelScope.launch {
        isLoading.value = true
        val response = selectedCategory.value?.id?.let { repo.deleteRestaurantCategory(it) }
        if (response?.status == Status.SUCCESS) {
            isLoading.value = false
            refreshCategories()
        } else {
            isLoading.value = false
        }
    }

    fun prepareForUpdate() = viewModelScope.launch {
        createMenuModel.description.value = selectedMenu.value.description.toString()
        createMenuModel.currency.value = selectedMenu.value.currency.toString()
        createMenuModel.name.value = selectedMenu.value.name.toString()
    }

    fun updateMenu() = viewModelScope.launch {
        isLoading.value = true
        val response = selectedMenu.value.id?.let { id ->
            repo.updateRestaurantMenu(createMenuModel.provideMenuModel(), id)
        }
        if (response?.status == Status.SUCCESS) {
            refreshMenus()
        } else {
            isLoading.value = false
        }
    }

    fun prepareForCategoryUpdate() {
        selectedCategory.value?.id?.let { createCategoryModel.menuId.value = it }
        createCategoryModel.name.value = selectedCategory.value?.name.toString()
    }


}