package com.blondhino.menuely.data.repo

import android.util.Log
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.TokenApi
import com.blondhino.menuely.data.common.model.AuthModel
import com.blondhino.menuely.data.common.request.LoginRequest
import com.blondhino.menuely.data.common.response.LoginUserResponse
import com.blondhino.menuely.data.database.dao.AuthDao
import com.blondhino.menuely.data.database.tables.AuthTableModel
import java.lang.Exception

class TokenRepo(
    private val tokenApi: TokenApi,
    private val authRepo: AuthRepo,
    private val responseHandler: ResponseHandler
) {


    suspend fun refreshToken(refreshToken: String):Response<AuthTableModel>{
        Log.d("refreshToken","called with: $refreshToken")
        return try {
            val response = tokenApi.refreshToken(refreshToken)
                responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }



}