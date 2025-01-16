package com.example.newapplicationjetpack.domain.use_cases.auth

import com.example.newapplicationjetpack.data.remote.DTO.LoginResponse
import com.example.newapplicationjetpack.data.remote.DTO.request.TwoStepReq
import com.example.newapplicationjetpack.data.remote.DTO.response.AppResponse
import com.example.newapplicationjetpack.domain.services.AuthService
import com.example.newapplicationjetpack.utils.handleHttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class Login2FaUseCase @Inject constructor(private val authService: AuthService) {
    sealed class Result {
        data object Loading : Result()
        data class Success(val data: LoginResponse) : Result()
        data class Error(val message: String) : Result()
    }

    operator fun invoke(email:String ,otp:String): Flow<Result> {
        return flow {
            emit(Result.Loading)
            try {
                val res = authService.twoStepVerify(TwoStepReq(email,otp))
                emit(Result.Success(res.data))
            }catch (e:HttpException){
                val errorResponse =  handleHttpException(e,AppResponse::class.java)
                emit(Result.Error(errorResponse))
            }
            catch (e:Exception){
                emit(Result.Error(e.message!!))
            }
        }
    }
}