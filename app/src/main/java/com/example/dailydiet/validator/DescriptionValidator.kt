package com.example.dailydiet.validator

class DescriptionValidator {

    companion object {

        fun validator(value: String): String? {
            var error: String? = null

            if (value.isEmpty()) {
                error = "A descrição é obrigatório"
            }

            return error
        }
    }
}