package com.example.dailydiet.model

data class FieldState<T>(
    val value: T,
    val error: String? = null,
    val valid: Boolean = false
)
