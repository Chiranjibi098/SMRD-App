package com.example.newapplicationjetpack.DTO

data class AppResponse<T>(
    val data: T,
    val message: String?,
    val description: String?
)