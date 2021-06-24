package com.blondhino.menuely.data.common.model

import androidx.compose.runtime.mutableStateOf
import com.blondhino.menuely.data.common.enums.LoginProcessType
import com.blondhino.menuely.data.common.enums.LoginProcessType.*

class SelectLoginProcessModel {
    private val loginAsUser = mutableStateOf(true)
    private val loginAsRestaurant = mutableStateOf(false)

    fun selectLoginOption(loginProcessType: LoginProcessType) {
        if (loginProcessType == LOGIN_AS_USER) {
            selectLoginAsUser()
        } else {
            selectLoginAsRestaurant()
        }
    }

    private fun selectLoginAsUser() {
        if (!loginAsUser.value) {
            loginAsUser.value = !loginAsUser.value
            loginAsRestaurant.value = !loginAsUser.value
        }

    }

    private fun selectLoginAsRestaurant() {
        if (!loginAsRestaurant.value) {
            loginAsRestaurant.value = !loginAsRestaurant.value
            loginAsUser.value = !loginAsRestaurant.value
        }

    }

    fun getSelection(): LoginProcessType {
        if (loginAsUser.value) {
            return LOGIN_AS_USER
        } else if (loginAsRestaurant.value) {
            return LOGIN_AS_RESTAURANT
        }
        return UNDEFINED
    }


}