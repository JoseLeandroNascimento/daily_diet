package com.example.dailydiet.validator

import java.text.SimpleDateFormat
import java.util.Locale

class TimeValidator {

    companion object{

        fun validator(value: String): String?{
            var error: String? = null

            if (!isHoraValida(value)) {
                error = "Formato de hora inválida"
            }

            return error

        }

        fun isHoraValida(hora: String): Boolean {
            return try {
                val formato = SimpleDateFormat("HH:mm", Locale.getDefault())
                formato.isLenient = false
                formato.parse(hora)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}