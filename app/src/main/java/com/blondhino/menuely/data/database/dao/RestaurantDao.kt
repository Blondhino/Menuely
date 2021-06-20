package com.blondhino.menuely.data.database.dao

import androidx.room.*
import com.blondhino.menuely.data.base.BaseDao
import com.blondhino.menuely.data.database.tables.RestaurantTableModel
import com.blondhino.menuely.data.database.tables.UserTableModel


@Dao
interface RestaurantDao : BaseDao<RestaurantTableModel> {

    @Query("SELECT * from restaurant_table")
    fun getRestaurant(): RestaurantTableModel?
}