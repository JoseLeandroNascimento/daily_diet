package com.example.dailydiet.validator

import java.text.SimpleDateFormat
import java.util.Locale

class DateValidator {

    companion object{

        fun validator(value: String): String?{

            var error: String? = null

            if (!isDataValida(value)) {
                error = "Formato de data inválida"
            }

            return error
        }

        fun isDataValida(data: String): Boolean {
            return try {
                val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                formato.isLenient = false
                formato.parse(data)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}