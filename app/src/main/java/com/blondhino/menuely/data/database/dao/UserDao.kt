package com.blondhino.menuely.data.database.dao

import androidx.room.*
import com.blondhino.menuely.data.base.BaseDao
import com.blondhino.menuely.data.database.tables.UserTableModel


@Dao
interface UserDao : BaseDao<UserTableModel> {

    @Query("SELECT * from user_table")
    fun getUser(): UserTableModel?


}