package com.blondhino.menuely.data.repo

import android.util.Log
import com.blondhino.menuely.data.base.BaseResponse
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.request.LoginUserRequest
import com.blondhino.menuely.data.common.response.LoginUserResponse
import java.lang.Exception
import javax.inject.Inject

class OnBoardingRepo(
    private val api: MenuelyApi,
    private val responseHandler: ResponseHandler
) {
    suspend fun loginUser(loginUserRequest : LoginUserRequest): Response<LoginUserResponse>{
        return try {
            val response = api.loginUser(loginUserRequest)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

}