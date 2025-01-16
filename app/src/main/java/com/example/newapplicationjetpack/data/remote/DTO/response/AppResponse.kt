package com.example.newapplicationjetpack.data.remote.DTO.response

data class AppResponse<T>(
    val data: T,
    val message: String?,
    val description: String?
)