package com.example.dailydiet.data

import com.example.dailydiet.model.Response
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


class SnackRepository @Inject constructor(private val snackDao: SnackDao) {

    suspend fun save(data: Snack): Response<Snack> {

        try {
            snackDao.save(data)
            return Response.Success(data = data)

        } catch (e: Exception) {
            return Response.Error(message = e.message ?: "Não foi possível salva os dados")
        }
    }

    fun findAll(): Response<Flow<List<Snack>>> {

        try {
            val data = snackDao.findAll()

            return Response.Success(data = data)

        } catch (e: Exception) {

            return Response.Error(message = e.message ?: "Não foi possível carregar os dados")
        }
    }

    suspend fun finById(id: Int): Response<Snack> {

        try {
            val data = snackDao.findById(id)
            return Response.Success(data = data)

        } catch (e: Exception) {
            return Response.Error(message = e.message ?: "Nenhum registro foi encontrado")
        }
    }

    suspend fun deleteById(id: Int): Response<Any> {
        try {
            snackDao.deleteById(id)
            return Response.Success(data = "")

        } catch (e: Exception) {
            return Response.Error(message = e.message ?: "Não foi possível deletar")
        }
    }
}