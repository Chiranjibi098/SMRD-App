package com.example.newapplicationjetpack.data.remote.api

import com.example.newapplicationjetpack.data.remote.DTO.response.AppResponse
import com.example.newapplicationjetpack.data.remote.DTO.ForgotPasswordRequest
import com.example.newapplicationjetpack.data.remote.DTO.LoginRequest
import com.example.newapplicationjetpack.data.remote.DTO.LoginResponse
import com.example.newapplicationjetpack.data.remote.DTO.request.ResetPasswordRequest
import com.example.newapplicationjetpack.data.remote.DTO.SignupRequest
import com.example.newapplicationjetpack.data.remote.DTO.request.TwoStepReq
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): AppResponse<Boolean>

    @POST("user/login-2fa")
    suspend fun twoStepVerify(@Body request: TwoStepReq): AppResponse<LoginResponse>

    @POST("user/forgotPassword")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): AppResponse<Boolean>

    @POST("user/resetPassword")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): AppResponse<Boolean>

    @POST("user/create")
    suspend fun create(@Body request: SignupRequest): AppResponse<LoginResponse>

}