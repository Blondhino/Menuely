package com.blondhino.menuely.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blondhino.menuely.data.database.dao.AuthDao
import com.blondhino.menuely.data.database.dao.RestaurantDao
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.database.tables.AuthTableModel
import com.blondhino.menuely.data.database.tables.RestaurantTableModel
import com.blondhino.menuely.data.database.tables.UserTableModel

@Database(
    entities = [
        UserTableModel::class,
        RestaurantTableModel::class,
        AuthTableModel::class
    ],
    version = 4,
    exportSchema = false
)

abstract class MenuelyDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun restaurantDao(): RestaurantDao

    abstract fun authDao(): AuthDao

}