package com.blondhino.menuely.data.common

import com.blondhino.menuely.data.common.base.BaseResponse

data class Response<out T>(var status: Status, val data: T) {

    companion object {

        fun <T> success(baseResponse: BaseResponse<T>): Response<T> =
            Response(Status.SUCCESS, baseResponse.data)


        fun <T> error(baseResponse: BaseResponse<T>, data: T): Response<T> =
            Response(Status.ERROR, data)
    }

        fun <T> loading(data: T): Response<T> = Response(Status.LOADING, data)
}