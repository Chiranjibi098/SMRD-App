package com.example.newapplicationjetpack.domain.services

import com.example.newapplicationjetpack.data.remote.DTO.*
import com.example.newapplicationjetpack.data.remote.DTO.request.ResetPasswordRequest
import com.example.newapplicationjetpack.data.remote.DTO.request.TwoStepReq
import com.example.newapplicationjetpack.data.remote.DTO.response.AppResponse
import com.example.newapplicationjetpack.data.remote.api.AuthApi
import javax.inject.Inject

class AuthService @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun login(username: String, password: String):AppResponse<Boolean> {
       return authApi.login(LoginRequest(username, password))
    }

    suspend fun createUser(request: SignupRequest): AppResponse<LoginResponse> {
        return authApi.create(request)
    }

    suspend fun forgotPassword(email:String): AppResponse<Boolean> {
        return authApi.forgotPassword(ForgotPasswordRequest(email))
    }

    suspend fun resetPassword(request: ResetPasswordRequest): AppResponse<Boolean> {
        return authApi.resetPassword(request)
    }

    suspend fun twoStepVerify(request: TwoStepReq): AppResponse<LoginResponse> {
        return authApi.twoStepVerify(request)
    }
}
