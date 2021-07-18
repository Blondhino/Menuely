package com.blondhino.menuely.di.module

import android.content.Context
import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.common.TokenApi
import com.blondhino.menuely.data.database.dao.AuthDao
import com.blondhino.menuely.data.repo.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Provides
    @Singleton
    fun provideOnBoardingRepo(menuelyApi: MenuelyApi, responseHandler: ResponseHandler):
            OnBoardingRepo = OnBoardingRepo(menuelyApi, responseHandler)

    @Provides
    @Singleton
    fun provideUserProfileRepo(
        menuelyApi: MenuelyApi,
        responseHandler: ResponseHandler,
        @ApplicationContext context: Context
    ): UserRepo = UserRepo(menuelyApi, responseHandler, context)


    @Provides
    @Singleton
    fun provideAuthRepo(
        authDao: AuthDao
    ): AuthRepo = AuthRepo(authDao)


    @Provides
    @Singleton
    fun provideTokenRepo(
        tokenApi: TokenApi,
        authRepo: AuthRepo,
        responseHandler: ResponseHandler
    ): TokenRepo = TokenRepo(tokenApi, authRepo, responseHandler)


    @Provides
    @Singleton
    fun provideRestaurantRepo(menuelyApi: MenuelyApi, responseHandler: ResponseHandler) =
        RestaurantRepo(menuelyApi, responseHandler)

    @Provides
    @Singleton
    fun provideMenusRepo(menuelyApi: MenuelyApi, responseHandler: ResponseHandler) =
        MenusRepo(menuelyApi, responseHandler)


}