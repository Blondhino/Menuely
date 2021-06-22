package com.blondhino.menuely.ui.onboarding

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blondhino.menuely.data.common.request.LoginUserRequest
import com.blondhino.menuely.data.repo.OnBoardingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.LoginModel
import com.blondhino.menuely.data.common.LoginStatus
import com.blondhino.menuely.data.common.Status
import com.blondhino.menuely.data.database.dao.UserDao
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repo: OnBoardingRepo,
    private val userDao: UserDao,
) : ViewModel() {

    val loginModel: LoginModel = LoginModel()
    private val _loginStatus: MutableLiveData<LoginStatus> = MutableLiveData()
    val loginStatus: LiveData<LoginStatus> get() = _loginStatus
    val loading = mutableStateOf(false)
    val messageText = mutableStateOf("")

    fun loginUser() = viewModelScope.launch {
        loading.value = true
        val response =
            repo.loginUser(LoginUserRequest(loginModel.email.value, loginModel.password.value))
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

    fun logoutUser() = viewModelScope.launch {
        userDao.getUser()?.let { userDao.delete(it) }
        _loginStatus.value = LoginStatus.LOGGED_OUT
    }

}