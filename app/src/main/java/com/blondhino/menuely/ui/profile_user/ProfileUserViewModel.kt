package com.blondhino.menuely.ui.profile_user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.model.UserProfileModel
import com.blondhino.menuely.data.common.request.LoginRequest
import com.blondhino.menuely.data.common.response.LoginUserResponse
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.repo.ProfileUserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class ProfileUserViewModel @Inject constructor(
    private val repo: ProfileUserRepo,
    private val userDao: UserDao
) : ViewModel() {

    val userProfileModel: UserProfileModel = UserProfileModel()
    var isFirstCall = mutableStateOf(true)

    fun fetchUserData() = viewModelScope.launch {
        repo.fetchMyUserProfile()
    }


}