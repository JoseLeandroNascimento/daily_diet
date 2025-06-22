package com.example.dailydiet.data

import com.example.dailydiet.model.Response
import jakarta.inject.Inject
import java.lang.Exception


class SnackRepository @Inject constructor(private val snackDao: SnackDao) {

    suspend fun save(data: Snack): Response {

        try {
            snackDao.save(data)
            return Response.Success(data = data)

        } catch (e: Exception) {
            return Response.Error(message = e.message ?: "Não foi possível salva os dados")
        }
    }
}