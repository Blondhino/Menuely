package com.blondhino.menuely.ui.onboarding.host

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blondhino.menuely.data.common.LoginStatus
import com.blondhino.menuely.data.common.LoginStatus.*
import com.blondhino.menuely.data.database.dao.RestaurantDao
import com.blondhino.menuely.data.database.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userDao: UserDao,
    private val restaurantDao: RestaurantDao
) : ViewModel() {
    private var _loginStatus: MutableLiveData<LoginStatus> = MutableLiveData()
    val loginStatus: LiveData<LoginStatus> get() = _loginStatus
    val loginStatusState = mutableStateOf(LOGGED_OUT)

    fun checkLoginStatus(){
        when {
            userDao.getUser() != null -> {
                _loginStatus.value = LOGGED_AS_USER
                loginStatusState.value=LOGGED_AS_USER
            }
            restaurantDao.getRestaurant() != null -> {
                _loginStatus.value = LOGGED_AS_RESTAURANT
                loginStatusState.value=LOGGED_AS_RESTAURANT
            }
            else -> {
                _loginStatus.value = LOGGED_OUT
                loginStatusState.value=LOGGED_OUT
            }
        }
    }
}