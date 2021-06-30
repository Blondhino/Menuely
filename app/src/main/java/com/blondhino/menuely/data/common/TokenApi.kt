package com.blondhino.menuely.data.common

import com.blondhino.menuely.data.base.BaseResponse
import com.blondhino.menuely.data.common.constants.Routes.REFRESH_TOKEN
import com.blondhino.menuely.data.common.model.AuthModel
import com.blondhino.menuely.data.database.tables.AuthTableModel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenApi {
    @FormUrlEncoded
    @POST(REFRESH_TOKEN)
    suspend fun refreshToken(@Field("refreshToken") refreshToken: String): BaseResponse<AuthTableModel>

}