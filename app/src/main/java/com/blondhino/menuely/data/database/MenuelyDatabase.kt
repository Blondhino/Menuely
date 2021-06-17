package com.blondhino.menuely.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blondhino.menuely.data.database.dao.UserDao
import com.blondhino.menuely.data.database.tables.UserModel

@Database(
    entities = [
        UserModel::class
    ],
    version = 1,
    exportSchema = false
)

abstract class MenuelyDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}