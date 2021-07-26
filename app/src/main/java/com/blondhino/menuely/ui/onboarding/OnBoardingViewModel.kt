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
import com.blondhino.menuely.data.common.enums.LoginProcessType
import com.blondhino.menuely.data.common.enums.LoginStatus
import com.blondhino.menuely.data.common.enums.RegistrationProcessType
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.*
import com.blondhino.menuely.data.common.request.RegisterRestaurantrRequest
import com.blondhino.menuely.data.common.request.RegisterUserRequest
import com.blondhino.menuely.data.database.dao.AuthDao
import com.blondhino.menuely.data.database.dao.RestaurantDao
import com.blondhino.menuely.data.database.dao.UserDao
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repo: OnBoardingRepo,
    private val userDao: UserDao,
    private val authDao: AuthDao,
    private val restaurantDao: RestaurantDao
) : ViewModel() {

    val loginModel: LoginModel = LoginModel()
    val registrationProcessModel: SelectRegistrationProcessModel = SelectRegistrationProcessModel()
    val registerAsUserModel: RegisterAsUserModel = RegisterAsUserModel()
    val registerAsRestaurantModel: RegisterAsRestaurantModel = RegisterAsRestaurantModel()
    val loginProcessModel: SelectLoginProcessModel = SelectLoginProcessModel()
    private val _loginStatus: MutableLiveData<LoginStatus> = MutableLiveData()
    val loginStatusState = mutableStateOf(LoginStatus.LOGGED_OUT)
    val loginStatus: LiveData<LoginStatus> get() = _loginStatus
    val loading = mutableStateOf(false)
    val messageText = mutableStateOf("")
    private var _successfulRegistration: MutableLiveData<Boolean> = MutableLiveData()
    val successfulRegistration: LiveData<Boolean> get() = _successfulRegistration

    private fun loginUser() = viewModelScope.launch {
        loading.value = true
        val response =
            repo.loginUser(LoginRequest(loginModel.email.value, loginModel.password.value))
        if (response.status == Status.SUCCESS) {
            response.data?.user?.let { userDao.insert(it) }
            response.data?.auth?.let { authDao.insert(it) }
            _loginStatus.value = LoginStatus.LOGGED_AS_USER
            loginStatusState.value=LoginStatus.LOGGED_AS_USER
            loading.value = false
        } else {

            messageText.value = response.message
            loading.value = false
        }
    }

    private fun loginRestaurant() = viewModelScope.launch {
        loading.value = true
        val response =
            repo.loginRestaurant(LoginRequest(loginModel.email.value, loginModel.password.value))
        if (response.status == Status.SUCCESS) {
            response.data?.restaurant?.let { restaurantDao.insert(it) }
            response.data?.auth?.let { authDao.insert(it) }
            _loginStatus.value = LoginStatus.LOGGED_AS_RESTAURANT
            loading.value = false
        } else {
            messageText.value = response.message
            loading.value = false
        }
    }

    private fun registerRestaurant() = viewModelScope.launch {
        loading.value = true
        if (registerAsRestaurantModel.areInputsValid()) {
            val response = repo.registerRestaurant(
                RegisterRestaurantrRequest(
                    registerAsRestaurantModel.email.value,
                    registerAsRestaurantModel.password.value,
                    registerAsRestaurantModel.restaurantName.value,
                    registerAsRestaurantModel.description.value,
                    registerAsRestaurantModel.country.value,
                    registerAsRestaurantModel.city.value,
                    registerAsRestaurantModel.address.value,
                    registerAsRestaurantModel.postalCode.value
                )
            )
            if (response.status == Status.SUCCESS) {
                loading.value = false
                messageText.value = response.data?.message.toString()
                _successfulRegistration.value = true
                registerAsRestaurantModel.clearData()

            } else {
                messageText.value = response.message
                loading.value = false
            }
        } else {
            loading.value = false
            messageText.value = registerAsRestaurantModel.errorMessage.value
        }
    }

    private fun registerUser() = viewModelScope.launch {
        loading.value = true
        if (registerAsUserModel.areInputsValid()) {
            val response = repo.registerUser(
                RegisterUserRequest(
                    registerAsUserModel.firstName.value,
                    registerAsUserModel.lastName.value,
                    registerAsUserModel.email.value,
                    registerAsUserModel.password.value
                )
            )
            if (response.status == Status.SUCCESS) {
                loading.value = false
                messageText.value = response.data?.message.toString()
                _successfulRegistration.value = true
                registerAsUserModel.clearData()

            } else {
                messageText.value = response.message
                loading.value = false
            }
        } else {
            loading.value = false
            messageText.value = registerAsUserModel.errorMessage.value
        }
    }


    fun register(registrationProcessType: RegistrationProcessType) {
        if (registrationProcessType == RegistrationProcessType.REGISTER_AS_USER) {
            registerUser()
        } else {
            registerRestaurant()
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
        authDao.getAuth()?.let { authDao.delete(it) }
        _loginStatus.value = LoginStatus.LOGGED_OUT
    }


}