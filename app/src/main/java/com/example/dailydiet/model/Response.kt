package com.example.dailydiet.model

import com.example.dailydiet.data.Snack

sealed class Response {

    data class Success(
        val data: Snack
    ): Response()

    data class Error(
        val message: String
    ): Response()

}