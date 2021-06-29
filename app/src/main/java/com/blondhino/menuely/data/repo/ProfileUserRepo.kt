package com.blondhino.menuely.data.repo

import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.UserModel
import com.blondhino.menuely.data.common.request.LoginRequest
import com.blondhino.menuely.data.common.response.LoginUserResponse
import java.lang.Exception

class ProfileUserRepo(
    private val api: MenuelyApi,
    private val responseHandler: ResponseHandler
) {

    suspend fun fetchMyUserProfile(): Response<UserModel> {
        return try {
            val response = api.fetchMyUserProfile()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }


}