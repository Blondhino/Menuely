package com.blondhino.menuely.data.common

import com.blondhino.menuely.data.base.BaseResponse
import com.blondhino.menuely.data.common.Routes.LOGIN_RESTAURANT
import com.blondhino.menuely.data.common.Routes.LOGIN_USER
import com.blondhino.menuely.data.common.Routes.REGISTER_USER
import com.blondhino.menuely.data.common.request.LoginRequest
import com.blondhino.menuely.data.common.request.RegisterUserRequest
import com.blondhino.menuely.data.common.response.EmptyResponse
import com.blondhino.menuely.data.common.response.LoginRestaurantResponse
import com.blondhino.menuely.data.common.response.LoginUserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MenuelyApi {

    @POST(LOGIN_USER)
    suspend fun loginUser(@Body loginRequest: LoginRequest): BaseResponse<LoginUserResponse>

    @POST(LOGIN_RESTAURANT)
    suspend fun loginRestaurant(@Body loginRequest: LoginRequest): BaseResponse<LoginRestaurantResponse>

    @POST(REGISTER_USER)
    suspend fun registerUser(@Body registerUserRequest: RegisterUserRequest): BaseResponse<EmptyResponse>


}