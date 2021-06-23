package com.blondhino.menuely.ui.onboarding

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blondhino.menuely.data.common.request.LoginRequest
import com.blondhino.menuely.data.repo.OnBoardingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.*
import com.blondhino.menuely.data.database.dao.RestaurantDao
import com.blondhino.menuely.data.database.dao.UserDao
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repo: OnBoardingRepo,
    private val userDao: UserDao,
    private val restaurantDao: RestaurantDao
) : ViewModel() {

    val loginModel: LoginModel = LoginModel()
    val registrationProcessModel: SelectRegistrationProcessModel = SelectRegistrationProcessModel()
    val loginProcessModel: SelectLoginProcessModel = SelectLoginProcessModel()
    private val _loginStatus: MutableLiveData<LoginStatus> = MutableLiveData()
    val loginStatus: LiveData<LoginStatus> get() = _loginStatus
    val loading = mutableStateOf(false)
    val messageText = mutableStateOf("")

    private fun loginUser() = viewModelScope.launch {
        loading.value = true
        val response =
            repo.loginUser(LoginRequest(loginModel.email.value, loginModel.password.value))
        if (response.status == Status.SUCCESS) {
            val let = response.data?.user?.let { userDao.insert(it) }
            _loginStatus.value = LoginStatus.LOGGED_AS_USER
            loading.value = false
        } else {
            Log.d("LoginUser", "err")
            Log.d("LoginUser", response.message)
            messageText.value = response.message
            loading.value = false
        }

    }

    private fun loginRestaurant() = viewModelScope.launch {
        loading.value = true
        val response =
            repo.loginRestaurant(LoginRequest(loginModel.email.value, loginModel.password.value))
        if (response.status == Status.SUCCESS) {
            val let = response.data?.restaurant?.let { restaurantDao.insert(it) }
            _loginStatus.value = LoginStatus.LOGGED_AS_RESTAURANT
            loading.value = false
        } else {
            Log.d("LoginUser", "err")
            Log.d("LoginUser", response.message)
            messageText.value = response.message
            loading.value = false
        }
    }

    fun login() {
        if (loginProcessModel.getSelection() == LoginProcessType.LOGIN_AS_USER) {
            loginUser()
        } else {
            loginRestaurant()
        }
    }

    fun logout() = viewModelScope.launch {
        userDao.getUser()?.let { userDao.delete(it) }
        restaurantDao.getRestaurant()?.let { restaurantDao.delete(it) }
        _loginStatus.value = LoginStatus.LOGGED_OUT
    }

}