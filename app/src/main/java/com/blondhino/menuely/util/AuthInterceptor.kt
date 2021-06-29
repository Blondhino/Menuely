package com.blondhino.menuely.util

import com.blondhino.menuely.data.common.constants.Routes.NO_AUTH_HEADER
import com.blondhino.menuely.data.database.dao.AuthDao
import com.blondhino.menuely.data.repo.AuthRepo
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    private val authRepo: AuthRepo
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request.header(NO_AUTH_HEADER)
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
    }

}
