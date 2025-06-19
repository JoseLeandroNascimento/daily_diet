package com.example.dailydiet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Snack(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val timestamp: Long,
    val isInside: Boolean
)
