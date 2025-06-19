package com.example.dailydiet.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Snack::class], version = 2)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun getSnackDao(): SnackDao
}