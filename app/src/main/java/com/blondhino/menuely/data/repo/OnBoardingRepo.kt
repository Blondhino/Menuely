package com.blondhino.menuely.data.repo

import android.util.Log
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.request.LoginUserRequest
import javax.inject.Inject

class OnBoardingRepo(private val api: MenuelyApi) {
    suspend fun loginUser(){
        api.loginUser(LoginUserRequest("user1@email.com","qqqqqq"))
    }

}