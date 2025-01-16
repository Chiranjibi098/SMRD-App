package com.example.newapplicationjetpack.domain.use_cases.auth

import com.example.newapplicationjetpack.data.remote.DTO.LoginResponse
import com.example.newapplicationjetpack.data.remote.DTO.SignupRequest
import com.example.newapplicationjetpack.domain.services.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SignupUserCase @Inject constructor(private val authService: AuthService) {
    sealed class Result {
        data object Loading : Result()
        data class Success(val data: LoginResponse) : Result()
        data class Error(val message: String) : Result()
    }

    operator fun invoke(email: String,firstName: String, lastName: String, password: String):Flow<Result> = flow{

        emit(Result.Loading)
        try {
            val resp = authService.createUser(SignupRequest(email,password,firstName,lastName,""))
            emit(Result.Success(resp.data))
        }catch (e:Exception){
            emit(Result.Error(e.message!!))
        }
        catch (e:Exception){
            emit(Result.Error("Error"))
        }
    }


}