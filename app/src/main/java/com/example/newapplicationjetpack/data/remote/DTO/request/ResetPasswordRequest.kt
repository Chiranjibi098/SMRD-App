package com.example.newapplicationjetpack.data.remote.DTO.request

data class ResetPasswordRequest(
    val forgotPasswordToken: String,
    val newPassword: String
)