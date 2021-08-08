package com.blondhino.menuely.data.repo

import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.Response
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.model.UserModel
import com.blondhino.menuely.data.common.request.CreateJobInvitationRequest
import com.blondhino.menuely.data.common.response.EmptyResponse


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

    suspend fun getJobInvitations() : Response<EmptyResponse>{
        return try{
            val response = api.getJobInvitations()
            responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleError(e.message.toString())
        }
    }

}