package com.blondhino.menuely.data.common

import com.blondhino.menuely.data.base.BaseResponse
import com.blondhino.menuely.data.common.constants.Routes.LOGIN_RESTAURANT
import com.blondhino.menuely.data.common.constants.Routes.LOGIN_USER
import com.blondhino.menuely.data.common.constants.Routes.MY_USER_PROFILE
import com.blondhino.menuely.data.common.constants.Routes.NO_AUTH_HEADER
import com.blondhino.menuely.data.common.constants.Routes.REFRESH_TOKEN
import com.blondhino.menuely.data.common.constants.Routes.REGISTER_RESTAURANT
import com.blondhino.menuely.data.common.constants.Routes.REGISTER_USER
import com.blondhino.menuely.data.common.model.UserModel
import com.blondhino.menuely.data.common.request.LoginRequest
import com.blondhino.menuely.data.common.request.RegisterRestaurantrRequest
import com.blondhino.menuely.data.common.request.RegisterUserRequest
import com.blondhino.menuely.data.common.response.EmptyResponse
import com.blondhino.menuely.data.common.response.LoginRestaurantResponse
import com.blondhino.menuely.data.common.response.LoginUserResponse
import com.blondhino.menuely.data.database.tables.AuthTableModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface MenuelyApi {

    @Headers(NO_AUTH_HEADER)
    @POST(LOGIN_USER)
    suspend fun loginUser(@Body loginRequest: LoginRequest): BaseResponse<LoginUserResponse>

    @Headers(NO_AUTH_HEADER)
    @POST(LOGIN_RESTAURANT)
    suspend fun loginRestaurant(@Body loginRequest: LoginRequest): BaseResponse<LoginRestaurantResponse>

    @Headers(NO_AUTH_HEADER)
    @POST(REGISTER_USER)
    suspend fun registerUser(@Body registerUserRequest: RegisterUserRequest): BaseResponse<EmptyResponse>

    @Headers(NO_AUTH_HEADER)
    @POST(REGISTER_RESTAURANT)
    suspend fun registerRestaurant(@Body registerRestaurantRequest: RegisterRestaurantrRequest): BaseResponse<EmptyResponse>

    @GET(MY_USER_PROFILE)
    suspend fun fetchMyUserProfile(): BaseResponse<UserModel>

}