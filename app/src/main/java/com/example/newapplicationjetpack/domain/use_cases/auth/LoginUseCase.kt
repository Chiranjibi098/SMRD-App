package com.example.newapplicationjetpack.domain.use_cases.auth

import android.content.Context
import com.example.newapplicationjetpack.data.remote.DTO.response.AppResponse
import com.example.newapplicationjetpack.domain.services.AuthService
import com.example.newapplicationjetpack.utils.PreferenceManager
import com.example.newapplicationjetpack.utils.handleHttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val service: AuthService,
    private val context: Context
) {
    sealed class Result {
        data object Loading : Result()
        data class Success(val data: Boolean) : Result()
        data class Error(val message: String) : Result()
    }

    operator fun invoke(username: String, password: String): Flow<Result> = flow {
        emit(Result.Loading)
        try {
            val response = service.login(username, password)
            val preferenceManager = PreferenceManager(context)
            preferenceManager.save(username, "loginEmail")
            emit(Result.Success(response.data))
        }catch (e:HttpException){
            emit(Result.Error(handleHttpException(e,AppResponse::class.java)))
        }
        catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e ->
        emit(Result.Error(e.message ?: "Unknown error occurred"))
    }
}
