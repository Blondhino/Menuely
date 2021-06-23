package com.blondhino.menuely.data.base

data class BaseResponse<T>(val data: T, val message: String)