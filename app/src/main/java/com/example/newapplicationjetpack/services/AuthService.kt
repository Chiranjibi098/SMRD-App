package com.example.newapplicationjetpack.services

import com.example.newapplicationjetpack.DTO.AppResponse
import com.example.newapplicationjetpack.DTO.ForgotPasswordRequest
import com.example.newapplicationjetpack.DTO.LoginRequest
import com.example.newapplicationjetpack.DTO.LoginResponse
import com.example.newapplicationjetpack.DTO.ResetPasswordRequest
import com.example.newapplicationjetpack.DTO.SignupRequest
import com.example.newapplicationjetpack.DTO.TwoStepReq
import com.example.newapplicationjetpack.retrofit.RetrofitInstance


class AuthService {
    suspend fun login(username: String, password: String) =
        RetrofitInstance.api.login(LoginRequest(username, password))

    suspend fun createUser(request: SignupRequest): LoginResponse {
        return RetrofitInstance.api.create(request)
    }
    suspend fun forgotPassword(request: ForgotPasswordRequest): AppResponse<Boolean> {
        return RetrofitInstance.api.forgotPassword(request)
    }

    suspend fun restPassword(request: ResetPasswordRequest): AppResponse<Boolean> {
        return RetrofitInstance.api.resetPassword(request)
    }

    suspend fun twoStepVerify(request: TwoStepReq): AppResponse<LoginResponse> {
        return RetrofitInstance.api.twoStepVerify(request)
    }
}
