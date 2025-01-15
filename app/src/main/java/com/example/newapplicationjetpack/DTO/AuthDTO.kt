package com.example.newapplicationjetpack.DTO


data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val accessToken: String, val refreshToken: String)
data class AppErrorResponse(
    val message: String,       // Short error message
    val description: String,   // Detailed description of the error
    val statusCode: Int        // HTTP status code
)

data class SignupRequest(val email: String, val password: String, val firstName: String, val lastName: String, val userRole: String)
data class ForgotPasswordRequest(val email: String);