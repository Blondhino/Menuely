package com.blondhino.menuely.ui.profile_user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blondhino.menuely.data.common.enums.Status
import com.blondhino.menuely.data.common.model.UserProfileModel
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.repo.ProfileUserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.logging.Handler
import javax.inject.Inject


@HiltViewModel
class ProfileUserViewModel @Inject constructor(
    private val repo: ProfileUserRepo,
    private val userDao: UserDao
) : ViewModel() {

    val userProfileModel: UserProfileModel = UserProfileModel()
    var isFirstCall = mutableStateOf(true)

    fun fetchUserData() = viewModelScope.launch {
        delay(5000)
        userProfileModel.profileImageUrl.value="https://yt3.ggpht.com/ytc/AKedOLTNn1yi2o0bGaJn6oF5KgycZ1vDxWWcVWRwd1lc=s176-c-k-c0x00ffffff-no-rj"
        userProfileModel.headerImageUrl.value="https://lh3.googleusercontent.com/proxy/51gxHYUfnOsUJderF61YUKHX3WmZy3eb3DGaIEIsvnkAT3FkyOHUIzpKMPa-R3Kg2rqy6DD9GcX8G-glJZ_yW6oALFiGQzalNQEEn9Eb2x2_CtuwM2z_ws4EQg_uSs8-mGGfrHco55Qc5mFiUryCkJ83lw"
        delay(5000)
        userProfileModel.profileImageUrl.value="https://lh3.googleusercontent.com/proxy/51gxHYUfnOsUJderF61YUKHX3WmZy3eb3DGaIEIsvnkAT3FkyOHUIzpKMPa-R3Kg2rqy6DD9GcX8G-glJZ_yW6oALFiGQzalNQEEn9Eb2x2_CtuwM2z_ws4EQg_uSs8-mGGfrHco55Qc5mFiUryCkJ83lw"
        userProfileModel.headerImageUrl.value="https://yt3.ggpht.com/ytc/AKedOLTNn1yi2o0bGaJn6oF5KgycZ1vDxWWcVWRwd1lc=s176-c-k-c0x00ffffff-no-rj"
       /* val response = repo.fetchMyUserProfile()
        if(response.status==Status.SUCCESS){
            Log.d("fetchUserData","succ")
        }*/
    }


}