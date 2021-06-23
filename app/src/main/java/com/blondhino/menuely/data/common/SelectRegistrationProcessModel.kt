package com.blondhino.menuely.data.common

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.rememberNavController
import com.blondhino.menuely.data.common.RegistrationProcessType.*

class SelectRegistrationProcessModel {
    val registerAsUser = mutableStateOf(true)
    val registerAsRestaurant = mutableStateOf(false)

    fun selectRegisterAsUser() {
        if (!registerAsUser.value) {
            registerAsUser.value = !registerAsUser.value
            registerAsRestaurant.value = !registerAsUser.value
        }

    }

    fun selectRegisterAsRestaurant() {
        if(!registerAsRestaurant.value){
            registerAsRestaurant.value = !registerAsRestaurant.value
            registerAsUser.value = !registerAsRestaurant.value
        }

    }

    fun getSelection():RegistrationProcessType{
        if(registerAsUser.value){
            return REGISTER_AS_USER
        }else if (registerAsRestaurant.value){
            return REGISTER_AS_RESTAURANT
        }
        return  UNDEFINED
    }


}