package com.example.newapplicationjetpack.data.remote.DTO


data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val accessToken: String, val refreshToken: String)
data class SignupRequest(val email: String, val password: String, val firstName: String, val lastName: String, val userRole: String)
data class ForgotPasswordRequest(val email: String);