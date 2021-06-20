package com.blondhino.menuely.di.module

import android.util.Log
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.ResponseHandler
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val CURL_LOG_TAG = "OkHttp CURL-->"

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        curlInterceptor: CurlInterceptor
    ): OkHttpClient {
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(curlInterceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://menuely.herokuapp.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMenuelyApi(retrofit: Retrofit): MenuelyApi = retrofit.create(MenuelyApi::class.java)

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger

    }

    @Provides
    @Singleton
    fun provideCurlInterceptor(): CurlInterceptor = CurlInterceptor(Loggable() {
        Log.d(CURL_LOG_TAG, it)
    })

    @Provides
    @Singleton
    fun provideResponseHandler():ResponseHandler = ResponseHandler()

}