package com.example.newapplicationjetpack.DTO

data class ResetPasswordRequest(
    val forgotPasswordToken: String,
    val newPassword: String
)