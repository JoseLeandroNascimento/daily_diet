package com.example.dailydiet.validator

class NameValidator {

    companion object{

        fun validator(value: String): String?{

            var error: String? = null

            if (value.isBlank()) {
                error = "O nome é obrigatório"
            }

            return error

        }
    }
}