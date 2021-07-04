package com.blondhino.menuely.ui.profile_user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.UserProfileModel
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.repo.ProfileUserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileUserViewModel @Inject constructor(
    private val repo: ProfileUserRepo,
    private val userDao: UserDao
) : ViewModel() {

    val userProfileModel: UserProfileModel = UserProfileModel(userDao)
    var isFirstCall = mutableStateOf(true)

    fun fetchUserData() = viewModelScope.launch {
       val response = repo.fetchMyUserProfile()
        if(response.status==Status.SUCCESS){
            response.data?.let { userProfileModel.setProfile(it) }
        }
    }




}