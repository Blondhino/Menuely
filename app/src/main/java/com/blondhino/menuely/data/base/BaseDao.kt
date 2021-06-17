package com.blondhino.menuely.data.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao <T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data : T)

    @Delete
    fun delete(data: T)

    @Update
    fun update(data: T)

}