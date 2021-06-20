package com.blondhino.menuely.data.common

import androidx.compose.runtime.mutableStateOf

class LoginModel {
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    fun onEmailChanged(email: String) {
        this.email.value = email
    }

    fun onPasswordChanged(password: String) {
        this.password.value = password
    }

}