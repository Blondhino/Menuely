package com.blondhino.menuely.util

import android.util.Log
import com.blondhino.menuely.data.common.constants.Routes.NO_AUTH
import com.blondhino.menuely.data.common.constants.Routes.NO_AUTH_HEADER
import com.blondhino.menuely.data.database.dao.AuthDao
import com.blondhino.menuely.data.repo.AuthRepo
import okhttp3.*

class AuthInterceptor(
    private val authRepo: AuthRepo
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        lateinit var exception: java.lang.Exception
        try {
            request.header(NO_AUTH)
                ?.let { // token is not needed for requests that contain NO_AUTH_HEADER
                    request = request.newBuilder().removeHeader(NO_AUTH_HEADER).build()
                    return chain.proceed(request)
                }
            val token: String? = authRepo.accessToken()
            token?.let {
                request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            }


            return chain.proceed(request)
        } catch (e: Exception) {
            exception =e
        }
        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(999)
            .message(exception.message.toString())
            .body(ResponseBody.create(null, "{${"asdasd"}}")).build()
    }

}



