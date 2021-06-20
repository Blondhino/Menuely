package com.blondhino.menuely.ui.onboarding

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blondhino.menuely.data.common.request.LoginUserRequest
import com.blondhino.menuely.data.repo.OnBoardingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.LoginModel
import com.blondhino.menuely.data.common.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor (private val repo: OnBoardingRepo) : ViewModel() {
val loginModel : LoginModel = LoginModel()

    fun loginUser(email: String, pass: String) = viewModelScope.launch {

        val response = repo.loginUser(LoginUserRequest(email, pass))
        if (response.status == Status.SUCCESS) {
            Log.d("LoginUser", "succ")
        } else {
            Log.d("LoginUser", "err")
            Log.d("LoginUser", response.message)
        }

    }

}