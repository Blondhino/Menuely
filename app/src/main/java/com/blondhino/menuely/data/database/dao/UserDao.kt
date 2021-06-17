package com.blondhino.menuely.data.database.dao

import androidx.room.*
import com.blondhino.menuely.data.base.BaseDao
import com.blondhino.menuely.data.database.tables.UserModel


@Dao
interface UserDao : BaseDao<UserModel> {

    @Query("SELECT * from user_table")
    fun getUser(): UserModel?
}