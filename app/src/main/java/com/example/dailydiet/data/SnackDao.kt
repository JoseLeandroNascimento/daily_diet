package com.example.dailydiet.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface SnackDao {

    @Insert
    suspend fun save(data: Snack)

}