package com.blondhino.menuely.data.common.model

import androidx.compose.runtime.mutableStateOf
import com.blondhino.menuely.data.common.enums.RegistrationProcessType
import com.blondhino.menuely.data.common.enums.RegistrationProcessType.*

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

    fun getSelection(): RegistrationProcessType {
        if(registerAsUser.value){
            return REGISTER_AS_USER
        }else if (registerAsRestaurant.value){
            return REGISTER_AS_RESTAURANT
        }
        return  UNDEFINED
    }


}