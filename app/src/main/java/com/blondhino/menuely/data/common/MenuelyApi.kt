package com.blondhino.menuely.data.common

import com.blondhino.menuely.data.common.Routes.LOGIN_USER
import com.blondhino.menuely.data.common.request.LoginUserRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface MenuelyApi {

@POST(LOGIN_USER)
suspend fun loginUser(@Body loginUserRequest: LoginUserRequest) : LoginUserRequest


}