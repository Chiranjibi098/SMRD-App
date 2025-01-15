package com.example.newapplicationjetpack.network

import com.example.newapplicationjetpack.DTO.AppResponse
import com.example.newapplicationjetpack.DTO.ForgotPasswordRequest
import com.example.newapplicationjetpack.DTO.LoginRequest
import com.example.newapplicationjetpack.DTO.LoginResponse
import com.example.newapplicationjetpack.DTO.ResetPasswordRequest
import com.example.newapplicationjetpack.DTO.SignupRequest
import com.example.newapplicationjetpack.DTO.TwoStepReq
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("user/login") // Replace with your API's login endpoint
    suspend fun login(@Body request: LoginRequest): AppResponse <Boolean>

    @POST("user/create")
    suspend fun create(@Body request: SignupRequest): LoginResponse

    @POST("user/forgotPassword")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): AppResponse<Boolean>

    @POST("user/resetPassword")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): AppResponse<Boolean>

    @POST("user/login-2fa")
    suspend fun twoStepVerify(@Body request: TwoStepReq): AppResponse<LoginResponse>
}