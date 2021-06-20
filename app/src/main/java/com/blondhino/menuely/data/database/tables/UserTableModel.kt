package com.blondhino.menuely.data.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "user_table")
class UserTableModel (
    @PrimaryKey
    val id: Int,
    var firstname: String?,
    var lastname: String?,
    val email: String?
)