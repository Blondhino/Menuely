package com.blondhino.menuely.data.database.dao

import androidx.room.*
import com.blondhino.menuely.data.database.tables.UserModel
import dagger.Provides


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userModel: UserModel)

    @Delete
    fun delete(userModel: UserModel)

    @Query("SELECT * from user_table")
    fun getUser(): UserModel?
}