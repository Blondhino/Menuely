package com.blondhino.menuely.data.common

import com.blondhino.menuely.data.base.BaseResponse

class ResponseHandler {

    fun  <T> handleSuccess(data : BaseResponse<T>): Response<T> {
        return Response.success(data)
    }

    fun <T> handleError(message:String): Response<T>{
        return Response.error(null,message.toString())
    }

}