package com.blondhino.menuely.data.database.dao

import androidx.room.*
import com.blondhino.menuely.data.base.BaseDao
import com.blondhino.menuely.data.database.tables.AuthTableModel
import com.blondhino.menuely.data.database.tables.RestaurantTableModel
import com.blondhino.menuely.data.database.tables.UserTableModel


@Dao
interface AuthDao : BaseDao<AuthTableModel> {

    @Query("SELECT * from auth_table")
    fun getAuth(): AuthTableModel?
}