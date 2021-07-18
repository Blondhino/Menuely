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
    val menuelyApi: MenuelyApi,
    val repo: MenusRepo,
    val restaurantDao: RestaurantDao
) :
    ViewModel() {
    private val initialFetchDone = mutableStateOf(false)
    val createMenuModel: CreateMenuModel = CreateMenuModel()
    val menus: MutableState<List<MenuModel>> = mutableStateOf(ArrayList())


    fun fetchMenus() {
        if (!initialFetchDone.value) {
            viewModelScope.launch {
                val response = restaurantDao.getRestaurant()?.id?.let { repo.getRestaurantMenus(it) }
                if(response?.status==Status.SUCCESS){
                    initialFetchDone.value=true
                   response.data?.let {
                       menus.value=it
                   }
                }
            }
        }
    }

}