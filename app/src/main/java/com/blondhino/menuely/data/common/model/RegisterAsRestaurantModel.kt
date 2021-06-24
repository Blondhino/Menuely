package com.blondhino.menuely.data.common.model

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf

class RegisterAsRestaurantModel {
    val email = mutableStateOf("")
    val restaurantName = mutableStateOf("")
    val address = mutableStateOf("")
    val postalCode = mutableStateOf("")
    val city = mutableStateOf("")
    val password = mutableStateOf("")
    val retypePassword = mutableStateOf("")
    val description = mutableStateOf("")
    val errorMessage = mutableStateOf("")
    val country = mutableStateOf("")

    fun onEmailChanged(email: String) {
        this.email.value = email
    }

    fun onCountryChanged(country: String) {
        this.country.value = country
    }

    fun onPostalCodeChanged(postalCode: String) {
        this.postalCode.value = postalCode
    }

    fun onCityChanged(city: String) {
        this.city.value = city
    }

    fun onDescriptionChanged(description: String) {
        this.description.value = description
    }

    fun onRestaurantNameChanged(restaurantName: String) {
        this.restaurantName.value = restaurantName
    }

    fun onAddressChanged(address: String) {
        this.address.value = address
    }

    fun onPasswordChanged(password: String) {
        this.password.value = password
    }

    fun onRetypePasswordChanged(retypePassword: String) {
        this.retypePassword.value = retypePassword
    }

    fun areInputsValid(): Boolean {
        if (email.value.isEmpty()
            || restaurantName.value.isEmpty()
            || address.value.isEmpty()
            || password.value.isEmpty()
            || retypePassword.value.isEmpty()
            || description.value.isEmpty()
            || city.value.isEmpty()
            || postalCode.value.isEmpty()
            || country.value.isEmpty()
        ) {
            errorMessage.value = "Please fill in all fields"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            errorMessage.value = "Email is not valid"
            return false
        } else if (password.value != retypePassword.value) {
            errorMessage.value = "Passwords are not the sameRegisterAsUserModel"
            return false
        }
        return true
    }

    fun clearData(){
        this.email.value=""
        this.country.value=""
        this.postalCode.value=""
        this.city.value=""
        this.description.value=""
        this.restaurantName.value=""
        this.address.value=""
        this.password.value=""
        this.retypePassword.value=""
    }


}