package com.blondhino.menuely.di.module

import com.blondhino.menuely.data.common.MenuelyApi
import com.blondhino.menuely.data.repo.OnBoardingRepo
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
    fun provideOnBoardingRepo(menuelyApi: MenuelyApi): OnBoardingRepo = OnBoardingRepo(menuelyApi)

}