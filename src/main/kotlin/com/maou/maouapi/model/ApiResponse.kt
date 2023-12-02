package com.maou.maouapi.model

data class ApiResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)
