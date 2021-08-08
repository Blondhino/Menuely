package com.blondhino.menuely.data.repo

import android.util.Log
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.RestaurantModel
import com.blondhino.menuely.data.common.model.UserModel
import com.blondhino.menuely.data.common.request.CreateJobInvitationRequest
import com.blondhino.menuely.data.common.response.EmptyResponse
import com.blondhino.menuely.data.database.tables.RestaurantTableModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class EmployeesRepo(
    private val api: MenuelyApi,
    private val responseHandler: ResponseHandler,
) {

    suspend fun searchUsers(search: String) : Response<ArrayList<UserModel>>{
        return  try {
            val response = api.searchUsers(search)
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

    suspend fun createJobInvitation(employeeId: Int) : Response<EmptyResponse>{
        return try{
            val request =CreateJobInvitationRequest(employeeId)
            val response = api.createJobInvitation(request)
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

}