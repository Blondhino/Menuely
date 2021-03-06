package com.blondhino.menuely.ui.menus

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.*
import com.blondhino.menuely.data.common.response.MenuCategoryResponse
import com.blondhino.menuely.data.common.response.MenuProductsResponse
import com.blondhino.menuely.data.database.dao.RestaurantDao
import com.blondhino.menuely.data.repo.MenusRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenusViewModel @Inject constructor(
    private val repo: MenusRepo,
    private val restaurantDao: RestaurantDao
) :
    ViewModel() {
    private val initialFetchDone = mutableStateOf(false)
    private val initialSingleFetchDone = mutableStateOf(false)
    private val initialCategoryFetchDone = mutableStateOf(false)
    private var lastSelectedMenuId = 0
    private var lastSelectedCategoryId = 0
    private var lastFetchedSingleMenuId =0
    val createMenuModel: CreateMenuModel = CreateMenuModel()
    val createCategoryModel: CreateCategoryModel = CreateCategoryModel()
    val createProductModel: CreateProductModel = CreateProductModel()
    val menus: MutableState<List<MenuModel>> = mutableStateOf(ArrayList())
    var categories: MutableState<List<MenuCategoryResponse>> = mutableStateOf(ArrayList())
    val products: MutableState<List<MenuProductsResponse>> = mutableStateOf(ArrayList())
    val selectedProduct: MutableState<MenuProductsResponse?> = mutableStateOf(null)
    val selectedMenu: MutableState<MenuModel> = mutableStateOf(MenuModel())
    val selectedCategory: MutableState<MenuCategoryResponse?> = mutableStateOf(null)
    val isLoading = mutableStateOf(false)
    val scannedMenu: MutableState<MenuModel?> = mutableStateOf(null)


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

    private fun refreshCategories() {
        viewModelScope.launch {
            isLoading.value = true

            val response = selectedMenu.value.id?.let { repo.getCategoriesForMenu(it) }
            response?.data?.let {
                if (response.status == Status.SUCCESS) {
                    isLoading.value = false
                    try {
                        val updated =
                            it.find { category -> category.id == selectedCategory.value?.id }
                        selectedCategory.value?.image?.url = updated?.image?.url
                        selectedCategory.value?.id = updated?.id
                        selectedCategory.value?.name = updated?.name
                    } catch (e: Exception) {
                    }
                    categories.value = categories.value.drop(categories.value.size)
                    delay(10)
                    categories.value = it.filter { it -> it.id != 0 }.toMutableList()
                }

            }
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

    fun updateProduct() = viewModelScope.launch {
        isLoading.value = true
        val response = selectedProduct.value?.id?.let {
            repo.updateProduct(
                createProductModel.provideProductModel(),
                it
            )
        }
        if (response?.status == Status.SUCCESS) {
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

    fun fetchSingleMenu(menuId: Int){
        if (menuId!=lastFetchedSingleMenuId) {
            lastFetchedSingleMenuId=menuId
            initialSingleFetchDone.value=false
            if(!initialSingleFetchDone.value){
                categories.value = categories.value.drop(categories.value.size)
                selectedMenu.value.name=""
                viewModelScope.launch {
                    isLoading.value = true
                    val response = repo.getSingleMenu(menuId)
                    if (response.status == Status.SUCCESS) {
                        response.data?.let {
                            selectedMenu.value = it
                            scannedMenu.value = it
                            fetchCategory()
                            isLoading.value = false
                        }
                    } else {
                        isLoading.value = false
                    }
                }
                initialSingleFetchDone.value=true
            }
        }
    }

    private fun refreshProducts() {
        viewModelScope.launch {
            isLoading.value = true
            val response = selectedCategory.value?.id?.let { repo.getProductsForMenu(it) }
            if (response?.status == Status.SUCCESS) {
                response.data?.let {
                    try {
                        val updated = it.find { product -> product.id == selectedProduct.value?.id }
                        selectedProduct.value?.image?.url = updated?.image?.url
                        selectedProduct.value?.name = updated?.name
                        selectedProduct.value?.price = updated?.price
                        selectedProduct.value?.description = updated?.description
                    } catch (e: Exception) {
                    }
                    products.value = products.value.drop(products.value.size)
                    delay(200)
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
                    for (category in categories.value) {
                        Log.d("categoryDetails", "name: ${category.name} id: ${category.id} ")
                    }
                }

                isLoading.value = false;
                initialCategoryFetchDone.value = true
                selectedMenu.value.id?.let {
                    lastSelectedMenuId = it
                }
            }
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
        selectedMenu.value.isActive?.let { createMenuModel.isActive.value = it }
    }

    fun setMenuActive() = viewModelScope.launch {
        createMenuModel.description.value = selectedMenu.value.description.toString()
        createMenuModel.currency.value = selectedMenu.value.currency.toString()
        createMenuModel.name.value = selectedMenu.value.name.toString()
        createMenuModel.isActive.value=true
        updateMenu()
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

    fun prepareForProductUpdate() {
        createProductModel.name.value = selectedProduct.value?.name.toString()
        createProductModel.price.value = selectedProduct.value?.price.toString()
        createProductModel.description.value = selectedProduct.value?.description.toString()
    }

    fun deleteProduct() = viewModelScope.launch {
        isLoading.value = true
        val response = selectedProduct.value?.id?.let { repo.deleteMenuProduct(it) }
        if (response?.status == Status.SUCCESS) {
            refreshProducts()
        } else {
            isLoading.value = false
        }
    }


}