package com.blondhino.menuely.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.blondhino.menuely.data.database.MenuelyDatabase
import com.blondhino.menuely.data.database.dao.RestaurantDao
import com.blondhino.menuely.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MenuelyDatabase {
        return Room.databaseBuilder(context, MenuelyDatabase::class.java, "menuely_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(database: MenuelyDatabase): UserDao = database.userDao()

    @Provides
    fun provideRestaurantDao(database: MenuelyDatabase):RestaurantDao = database.restaurantDao()
}