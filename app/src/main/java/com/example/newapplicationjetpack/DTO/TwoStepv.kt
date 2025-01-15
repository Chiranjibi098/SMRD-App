package com.example.newapplicationjetpack.DTO

data class TokenData(
    val accessToken: String,
    val refreshToken: String,
    val message: String,
    val description: String
)