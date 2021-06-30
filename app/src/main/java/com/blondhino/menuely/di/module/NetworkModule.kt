package com.blondhino.menuely.di.module

import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.TokenApi
import com.blondhino.menuely.data.common.constants.Routes.BASE_URL
import com.blondhino.menuely.data.database.dao.AuthDao
import com.blondhino.menuely.data.repo.AuthRepo
import com.blondhino.menuely.data.repo.TokenRepo
import com.blondhino.menuely.util.AuthInterceptor
import com.blondhino.menuely.util.TokenAuthenticator
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
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)

        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
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
        logger.level = HttpLoggingInterceptor.Level.HEADERS
        return logger

    }

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler = ResponseHandler()


    @Provides
    @Singleton
    fun provideAuthInterceptor(authDao: AuthDao): AuthInterceptor =
        AuthInterceptor(AuthRepo(authDao))

    @Provides
    @Singleton
    fun provideTokenApi(): TokenApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient().newBuilder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(TokenApi::class.java)


    @Provides
    @Singleton
    fun provideTokenAuthenticator(tokenRepo: TokenRepo, authRepo: AuthRepo): TokenAuthenticator =
        TokenAuthenticator(tokenRepo, authRepo)

}