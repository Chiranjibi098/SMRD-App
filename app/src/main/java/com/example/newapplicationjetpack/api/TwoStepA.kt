package com.example.newapplicationjetpack.api

import com.example.newapplicationjetpack.DTO.AppResponse
import com.example.newapplicationjetpack.DTO.LoginRequest
import com.example.newapplicationjetpack.DTO.TokenData
import retrofit2.http.Body
import retrofit2.http.POST

interface TwoStepA {
    @POST("user/login-2fa")
    suspend fun login(@Body request: TokenData): AppResponse<Boolean>
}