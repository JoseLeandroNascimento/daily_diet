package com.example.dailydiet.data

import jakarta.inject.Inject


class SnackRepository @Inject constructor(private val snackDao: SnackDao) {

    suspend fun save(data: Snack){
        snackDao.save(data)
    }
}