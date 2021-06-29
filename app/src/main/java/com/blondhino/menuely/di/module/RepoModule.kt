package com.blondhino.menuely.di.module

import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.common.ResponseHandler
import com.blondhino.menuely.data.database.dao.AuthDao
import com.blondhino.menuely.data.repo.AuthRepo
import com.blondhino.menuely.data.repo.OnBoardingRepo
import com.blondhino.menuely.data.repo.ProfileUserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        responseHandler: ResponseHandler
    ): ProfileUserRepo = ProfileUserRepo(menuelyApi, responseHandler)


    @Provides
    @Singleton
    fun provideAuthRepo(
        authDao: AuthDao
    ): AuthRepo = AuthRepo(authDao)



}