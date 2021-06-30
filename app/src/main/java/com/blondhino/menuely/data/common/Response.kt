package com.blondhino.menuely.data.common

import android.util.Log
import com.blondhino.menuely.data.base.BaseResponse
import com.blondhino.menuely.data.common.enums.Status

data class Response<out T>(var status: Status, val data: T?, val message: String) {

    companion object {

        fun <T> success(baseResponse: BaseResponse<T>): Response<T> =
            Response(Status.SUCCESS, baseResponse.data,"")


        fun <T> error(baseResponse: BaseResponse<T>?, message: String): Response<T> {
            Log.d("EroorCall",message)
            return Response(Status.ERROR, baseResponse?.data,message)
        }
    }

        fun <T> loading(data: T): Response<T> = Response(Status.LOADING, data,"")
}