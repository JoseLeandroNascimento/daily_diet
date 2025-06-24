package com.example.dailydiet.data

import com.example.dailydiet.model.Response
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first


class SnackRepository @Inject constructor(private val snackDao: SnackDao) {

    suspend fun save(data: Snack): Response<Snack> {

        try {
            snackDao.save(data)
            return Response.Success(data = data)

        } catch (e: Exception) {
            return Response.Error(message = e.message ?: "Não foi possível salva os dados")
        }
    }

    suspend fun update(data: Snack): Response<Snack> {
        try {
            snackDao.update(data)
            return Response.Success(data = data)

        } catch (e: Exception) {
            return Response.Error(message = e.message ?: "Não foi possível alterar os dados")
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

    suspend fun percentSnackPositive(): Double {
        val total = countAllSnack().toDouble()
        val snackPositive = countAllSnackPositive()
        val percentage = (snackPositive * 100) / total

        return percentage
    }

    suspend fun percentSnackNegative(): Double {
        val total = countAllSnack().toDouble()
        val snackNegative = countAllSnackNegative()
        val percentage = (snackNegative * 100) / total

        return percentage
    }

    suspend fun countAllSnack(): Int {
        return snackDao.countAllSnack()
    }

    suspend fun countAllSnackPositive(): Int {
        return snackDao.countAllSnackPositive()
    }

    suspend fun countAllSnackNegative(): Int {
        return snackDao.countAllSnackNegative()
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