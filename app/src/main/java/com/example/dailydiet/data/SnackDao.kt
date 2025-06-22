package com.example.dailydiet.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SnackDao {

    @Insert
    suspend fun save(data: Snack)

    @Update
    suspend fun update(data: Snack)

    @Query("SELECT * FROM Snack ORDER BY timestamp DESC")
    fun findAll(): Flow<List<Snack>>

    @Query("SELECT * FROM Snack WHERE id = :id")
    suspend fun findById(id: Int): Snack

    @Query("DELETE FROM Snack WHERE id = :id")
    suspend fun deleteById(id: Int)

}