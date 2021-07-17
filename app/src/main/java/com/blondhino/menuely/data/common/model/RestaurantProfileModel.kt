package com.blondhino.menuely.data.common.model

import androidx.compose.runtime.mutableStateOf

class RestaurantProfileModel() {
    val id = mutableStateOf<Int?>(0)
    val email = mutableStateOf("")
    val name = mutableStateOf("")
    val description = mutableStateOf("")
    val country = mutableStateOf("")
    val city = mutableStateOf("")
    val address = mutableStateOf("")
    val postalCode = mutableStateOf("")
    val profileImage = mutableStateOf("")
    val coverImage = mutableStateOf("")
    val createdAt = mutableStateOf<Long>(0)
    val updatedAt = mutableStateOf<Long>(0)


    fun setData(restaurant: RestaurantModel) {
        id.value=restaurant.id
        email.value=restaurant.email.orEmpty()
        name.value=restaurant.name.orEmpty()
        description.value=restaurant.description.orEmpty()
        country.value=restaurant.country.orEmpty()
        city.value=restaurant.city.orEmpty()
        address.value=restaurant.address.orEmpty()
        postalCode.value=restaurant.postalCode.orEmpty()
        profileImage.value=restaurant.profileImage?.url.orEmpty()
        coverImage.value=restaurant.coverImage?.url.orEmpty()
    }

    fun clearData() {
        id.value=null
            email.value=""
            name.value=""
            description.value=""
            country.value=""
            city.value=""
            address.value=""
            postalCode.value=""
            profileImage.value=""
            coverImage.value=""
    }

}