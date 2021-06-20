package com.blondhino.menuely.data.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "restaurant_table")
class RestaurantTableModel (
    @PrimaryKey
    val id: Int,
    val email: String?,
    var name: String?,
    var description: String?,
    var country: String?,
    var city: String?,
    var address: String?,
    var postalCode: String?,
)