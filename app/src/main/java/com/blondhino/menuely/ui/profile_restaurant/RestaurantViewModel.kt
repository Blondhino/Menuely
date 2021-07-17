package com.blondhino.menuely.ui.profile_restaurant

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.model.RestaurantModel
import com.blondhino.menuely.data.common.model.RestaurantProfileModel
import com.blondhino.menuely.data.repo.RestaurantRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val menuelyApi: MenuelyApi,
    private val repo: RestaurantRepo
) : ViewModel() {

    var restaurant : RestaurantProfileModel = RestaurantProfileModel()
    private var firstFetchDone = mutableStateOf(false)
    private var lastFetchedId = mutableStateOf(0)

    fun getSingleRestaurant(id: Int) {
        if(!firstFetchDone.value || lastFetchedId.value!=id) {
            viewModelScope.launch {
                restaurant.clearData()
                val response = repo.getSingleRestaurant(id)
                response.data?.let {
                    Log.d("collectedData", response.data.name.orEmpty())
                    restaurant.setData(it)
                    firstFetchDone.value=true
                    lastFetchedId.value=id
                }
            }
        }
    }



}