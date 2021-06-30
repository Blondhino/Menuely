package com.blondhino.menuely.util

import android.util.Log
import com.blondhino.menuely.data.repo.AuthRepo
import com.blondhino.menuely.data.repo.TokenRepo
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val tokenRepo: TokenRepo, private val authRepo: AuthRepo) :
    Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        Log.d("TokenIntercept", "authenticate")
        val resp = runBlocking { authRepo.refreshToken()?.let { tokenRepo.refreshToken(it) } }
        resp?.data?.let {
            authRepo.insertNewAuth(it)
        }
        return try {
            response.request.newBuilder()
                .header("Authorization", "Bearer ${resp?.data?.accessToken}")
                .build()
        }catch (e:Exception){

            return null
        }

    }

}