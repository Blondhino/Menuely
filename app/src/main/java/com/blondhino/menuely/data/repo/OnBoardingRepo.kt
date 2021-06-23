package com.blondhino.menuely.data.repo

import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.request.LoginRequest
import com.blondhino.menuely.data.common.response.LoginRestaurantResponse
import com.blondhino.menuely.data.common.response.LoginUserResponse
import java.lang.Exception

class OnBoardingRepo(
    private val api: MenuelyApi,
    private val responseHandler: ResponseHandler
) {
    suspend fun loginUser(loginRequest : LoginRequest): Response<LoginUserResponse>{
        return try {
            val response = api.loginUser(loginRequest)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun loginRestaurant(loginRequest : LoginRequest): Response<LoginRestaurantResponse>{
        return try {
            val response = api.loginRestaurant(loginRequest)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

}