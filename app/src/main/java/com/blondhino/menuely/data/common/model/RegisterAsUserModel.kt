package com.blondhino.menuely.data.common.model

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf

class RegisterAsUserModel {
    val errorMessage = mutableStateOf("")
    val email = mutableStateOf("")
    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val password = mutableStateOf("")
    val retypePassword = mutableStateOf("")

    fun onEmailChanged(email: String) {
        this.email.value = email
    }

    fun onFirstNameChanged(firstName: String) {
        this.firstName.value = firstName
    }

    fun onLastNameChanged(lastName: String) {
        this.lastName.value = lastName
    }

    fun onPasswordChanged(password: String) {
        this.password.value = password
    }

    fun onRetypePasswordChanged(retypePassword: String) {
        this.retypePassword.value = retypePassword
    }

    fun areInputsValid(): Boolean {
        if (email.value.isEmpty()
            || firstName.value.isEmpty()
            || lastName.value.isEmpty()
            || password.value.isEmpty()
            || retypePassword.value.isEmpty()
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

    fun clearData() {
        this.email.value = ""
        this.firstName.value = ""
        this.lastName.value = ""
        this.password.value = ""
        this.retypePassword.value = ""
    }

}