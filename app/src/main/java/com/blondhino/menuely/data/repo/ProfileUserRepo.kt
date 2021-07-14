package com.blondhino.menuely.data.repo

import android.content.Context
import android.util.Log
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.UserModel
import com.blondhino.menuely.data.common.response.EmptyResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class ProfileUserRepo(
    private val api: MenuelyApi,
    private val responseHandler: ResponseHandler,
    private val context : Context
) {

    suspend fun fetchMyUserProfile(): Response<UserModel> {
        return try {
            val response = api.fetchMyUserProfile()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun updateCoverImage(multipart: MultipartBody.Part): Response<EmptyResponse>? {
        return try {
            val response = api.updateImageOnProfile(multipart,createPartFromString("profile"))
            responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            Log.d("EXception",e.message.toString())
            responseHandler.handleError(e.message.toString())
        }
    }

    private fun createPartFromString(kind: String): RequestBody? {
        return kind
            .toRequestBody(MultipartBody.FORM)
    }


}