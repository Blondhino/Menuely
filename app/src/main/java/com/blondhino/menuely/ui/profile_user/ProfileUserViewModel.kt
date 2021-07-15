package com.blondhino.menuely.ui.profile_user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.UserProfileModel
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.repo.ProfileUserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject


@HiltViewModel
class ProfileUserViewModel @Inject constructor(
    private val repo: ProfileUserRepo,
    private val userDao: UserDao
) : ViewModel() {

    val userProfileModel: UserProfileModel = UserProfileModel(userDao)
    var isFirstCall = mutableStateOf(true)
    val isLoading = mutableStateOf(false)
    private var updateLastnameProcess: Job? = null
    private var updateFirstnameProcess: Job? = null

    fun fetchUserData() = viewModelScope.launch {
        val response = repo.fetchMyUserProfile()
        isLoading.value=true
        if (response.status == Status.SUCCESS) {
            response.data?.let { userProfileModel.setProfile(it) }
            isLoading.value=false;
        }else{
            isLoading.value=false
        }
    }

    fun updateProfileImage(imageMultipart : MultipartBody.Part) = viewModelScope.launch {
        isLoading.value=true
        val response = repo.updateProfileImage(imageMultipart)
        if(response?.status==Status.SUCCESS){
            isLoading.value=false
            fetchUserData()
        }else{
            isLoading.value=false
        }
    }

    fun updateCoverImage(imageMultipart : MultipartBody.Part) = viewModelScope.launch {
        isLoading.value=true
        val response = repo.updateCoverImage(imageMultipart)
        if(response?.status == Status.SUCCESS){
            isLoading.value=false
            fetchUserData()
        }else{
            isLoading.value=false
        }
    }

    fun updateLastName(lastName:String) {
        updateLastnameProcess?.cancel()
        updateLastnameProcess = viewModelScope.launch {
            delay(500)
            val response = repo.updateLastName(lastName)
        }
    }

    fun updateFirstName(firstName:String){
        updateFirstnameProcess?.cancel()
        updateFirstnameProcess = viewModelScope.launch {
            delay(500)
            val response = repo.updateFirstName(firstName)
        }
    }


}