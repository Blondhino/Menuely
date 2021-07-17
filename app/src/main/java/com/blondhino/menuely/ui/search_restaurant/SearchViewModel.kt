package com.blondhino.menuely.ui.search_restaurant

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.model.RestaurantModel
import com.blondhino.menuely.data.common.model.SearchModel
import com.blondhino.menuely.data.repo.RestaurantRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: RestaurantRepo) : ViewModel() {

    val searchModel: SearchModel = SearchModel()
    val restaurants: MutableState<List<RestaurantModel>> = mutableStateOf(ArrayList())
    private val initialSearchDone = mutableStateOf(false)
    private var searchRestaurantJob: Job? = null
    val isLoading = mutableStateOf(false)
    val emptyStateVisible = mutableStateOf(false)
    val clickedRestaurantId = mutableStateOf(0)

    fun search() {
        searchRestaurantJob?.cancel()
        searchRestaurantJob = viewModelScope.launch {
            isLoading.value=true
            delay(500)
            val response = repo.searchRestaurants(searchModel.search.value)
            response.data?.let {
                restaurants.value = it
                isLoading.value=false
                emptyStateVisible.value = response.data.size==0
            }

            Log.d("searchResp", "${restaurants.value.size}")
        }

    }

    fun initialSearch() {
        if (!initialSearchDone.value) {
            isLoading.value=true
            viewModelScope.launch {
                val response = repo.searchRestaurants("")
                response.data?.let {
                    restaurants.value = it
                    isLoading.value=false

                }
            }
            initialSearchDone.value = true
        }
    }

}