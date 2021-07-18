package com.blondhino.menuely.ui.profile_restaurant

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.RestaurantModel
import com.blondhino.menuely.data.common.model.RestaurantProfileModel
import com.blondhino.menuely.data.repo.RestaurantRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val menuelyApi: MenuelyApi,
    private val repo: RestaurantRepo
) : ViewModel() {

    var restaurant: RestaurantProfileModel = RestaurantProfileModel()
    var myRestaurantProfile: RestaurantProfileModel = RestaurantProfileModel()
    private var firstFetchDone = mutableStateOf(false)
    private var initialProfileFetchDone = mutableStateOf(false)
    private var lastFetchedId = mutableStateOf(0)
    private var updateNameProcess: Job? = null
    private var updateAddressProcess: Job? = null
    private var updateDescriptionProcess: Job? = null
    private var updatePostalCodeProcess: Job? = null
    private var updateCountryProcess: Job? = null
    private var updateCityProcess: Job? = null
    val isLoading = mutableStateOf(false)


    fun fetchMyRestaurantProfile() {
        if (!initialProfileFetchDone.value) {
            viewModelScope.launch {
                val response = repo.getMyRestaurantProfile()
                response.data?.let {
                    myRestaurantProfile.setData(it)
                }
            }
            initialProfileFetchDone.value = true
        }
    }

    fun refreshMyRestaurantProfile() = viewModelScope.launch {
        val response = repo.getMyRestaurantProfile()
        response.data?.let {
            myRestaurantProfile.setData(it)
        }
    }



    fun getSingleRestaurant(id: Int) {
        if (!firstFetchDone.value || lastFetchedId.value != id) {
            viewModelScope.launch {
                restaurant.clearData()
                val response = repo.getSingleRestaurant(id)
                response.data?.let {
                    Log.d("collectedData", response.data.name.orEmpty())
                    restaurant.setData(it)
                    firstFetchDone.value = true
                    lastFetchedId.value = id
                }
            }
        }
    }

    fun updateName(name: String) {
        updateNameProcess?.cancel()
        viewModelScope.launch {
            delay(500)
            repo.updateRestaurantName(name)
        }
    }

    fun updateAddress(address: String) {
        updateAddressProcess?.cancel()
        viewModelScope.launch {
            delay(500)
            repo.updateRestaurantAddress(address)
        }
    }

    fun updateDescription(description: String) {
        updateDescriptionProcess?.cancel()
        viewModelScope.launch {
            delay(500)
            repo.updateRestaurantDescription(description)
        }
    }

    fun updatePostalCode(postalCode: String) {
        updatePostalCodeProcess?.cancel()
        viewModelScope.launch {
            delay(500)
            repo.updateRestaurantPostalCode(postalCode)
        }
    }

    fun updateCountry(country: String) {
        updateCountryProcess?.cancel()
        viewModelScope.launch {
            delay(500)
            repo.updateRestaurantCountry(country)
        }
    }

    fun updateCity(city: String) {
        updateCityProcess?.cancel()
        viewModelScope.launch {
            delay(500)
            repo.updateRestaurantCity(city)
        }
    }

    fun updateCoverImage(imageMultipart: MultipartBody.Part) = viewModelScope.launch {
        isLoading.value = true
        val response = repo.updateCoverImage(imageMultipart)
        if (response?.status == Status.SUCCESS) {
            isLoading.value = false
            refreshMyRestaurantProfile()
        } else {
            isLoading.value = false
        }
    }

    fun updateProfileImage(imageMultipart: MultipartBody.Part) = viewModelScope.launch {
        isLoading.value = true
        val response = repo.updateProfileImage(imageMultipart)
        if (response?.status == Status.SUCCESS) {
            isLoading.value = false
            refreshMyRestaurantProfile()
        } else {
            isLoading.value = false
        }
    }


}