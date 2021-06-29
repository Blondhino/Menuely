package com.blondhino.menuely.data.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "auth_table")
class AuthTableModel (
    @PrimaryKey
    val id: Int,
    val accessToken: String?,
    var refreshToken: String?,
)