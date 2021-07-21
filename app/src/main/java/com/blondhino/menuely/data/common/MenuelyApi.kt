package com.blondhino.menuely.data.common

import com.blondhino.menuely.data.base.BaseResponse
import com.blondhino.menuely.data.common.constants.Routes.CREATE_CATEGORY
import com.blondhino.menuely.data.common.constants.Routes.CREATE_MENU
import com.blondhino.menuely.data.common.constants.Routes.CREATE_PRODUCT
import com.blondhino.menuely.data.common.constants.Routes.GET_CATEGORIES
import com.blondhino.menuely.data.common.constants.Routes.GET_PRODUCTS
import com.blondhino.menuely.data.common.constants.Routes.LOGIN_RESTAURANT
import com.blondhino.menuely.data.common.constants.Routes.LOGIN_USER
import com.blondhino.menuely.data.common.constants.Routes.MENUS
import com.blondhino.menuely.data.common.constants.Routes.MY_USER_PROFILE
import com.blondhino.menuely.data.common.constants.Routes.NO_AUTH_HEADER
import com.blondhino.menuely.data.common.constants.Routes.REGISTER_RESTAURANT
import com.blondhino.menuely.data.common.constants.Routes.REGISTER_USER
import com.blondhino.menuely.data.common.constants.Routes.RESTAURANTS
import com.blondhino.menuely.data.common.constants.Routes.UPDATE_IMAGE_ON_PROFILE
import com.blondhino.menuely.data.common.constants.Routes.UPDATE_IMAGE_ON_RESTAURANT_PROFILE
import com.blondhino.menuely.data.common.constants.Routes.UPDATE_RESTAURANT_PROFILE
import com.blondhino.menuely.data.common.constants.Routes.UPDATE_USER_PROFILE
import com.blondhino.menuely.data.common.model.MenuModel
import com.blondhino.menuely.data.common.model.RestaurantModel
import com.blondhino.menuely.data.common.model.UserModel
import com.blondhino.menuely.data.common.request.LoginRequest
import com.blondhino.menuely.data.common.request.RegisterRestaurantrRequest
import com.blondhino.menuely.data.common.request.RegisterUserRequest
import com.blondhino.menuely.data.common.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

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

    @Multipart
    @PATCH(UPDATE_IMAGE_ON_PROFILE)
    suspend fun updateImageOnProfile(
        @Part image: MultipartBody.Part,
        @Part("kind") kind: RequestBody?
    ): BaseResponse<EmptyResponse>

    @Multipart
    @PATCH(UPDATE_IMAGE_ON_RESTAURANT_PROFILE)
    suspend fun updateImageOnRestaurantProfile(
        @Part image: MultipartBody.Part,
        @Part("kind") kind: RequestBody?
    ): BaseResponse<EmptyResponse>

    @Multipart
    @POST(CREATE_CATEGORY)
    suspend fun createCategory(
        @Part image: MultipartBody.Part,
        @Part("menuId") menuId: RequestBody,
        @Part("name") name: RequestBody,
    ): BaseResponse<EmptyResponse>

    @Multipart
    @POST(CREATE_PRODUCT)
    suspend fun createProduct(
        @Part image: MultipartBody.Part,
        @Part("categoryId") categoryId: RequestBody,
        @Part("currency") currency: RequestBody,
        @Part("price") price: RequestBody,
        @Part("description") description: RequestBody,
        @Part("name") name: RequestBody,
    ): BaseResponse<EmptyResponse>

    @PATCH(UPDATE_USER_PROFILE)
    suspend fun updateUserProfile(@Body userModel: UserModel): BaseResponse<EmptyResponse>

    @GET(RESTAURANTS)
    suspend fun searchRestaurants(@Query("search") search: String): BaseResponse<ArrayList<RestaurantModel>>

    @GET("$RESTAURANTS/{id}")
    suspend fun getSingleRestaurant(@Path("id") id: Int): BaseResponse<RestaurantModel>

    @GET(MENUS)
    suspend fun getRestaurantMenus(@Query("restaurantId") restaurantId: Int): BaseResponse<ArrayList<MenuModel>>

    @GET("$RESTAURANTS/me")
    suspend fun getMyRestaurantProfile(): BaseResponse<RestaurantModel>

    @POST(CREATE_MENU)
    suspend fun createRestaurantMenu(@Body menuModel: MenuModel): BaseResponse<EmptyResponse>

    @PATCH(UPDATE_RESTAURANT_PROFILE)
    suspend fun updateRestaurantProfile(@Body restaurantModel: RestaurantModel): BaseResponse<EmptyResponse>

    @GET(GET_CATEGORIES)
    suspend fun getCategoriesForMenu(@Query("menuId") menuId: Int): BaseResponse<ArrayList<MenuCategoryResponse>>

    @GET(GET_PRODUCTS)
    suspend fun getProductsForMenu(@Query("categoryId") categoryId: Int): BaseResponse<ArrayList<MenuProductsResponse>>


}