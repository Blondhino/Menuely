package com.blondhino.menuely.data.common

import android.util.Log
import com.blondhino.menuely.data.base.BaseResponse
import com.blondhino.menuely.data.common.enums.Status

data class Response<out T>(var status: Status, val data: T?, val message: String) {

    companion object {

        fun <T> success(baseResponse: BaseResponse<T>): Response<T> =
            Response(Status.SUCCESS, baseResponse.data,"")


        fun <T> error(baseResponse: BaseResponse<T>?, message: String): Response<T> {
            return Response(Status.ERROR, null,message.toString())
        }



    }


}