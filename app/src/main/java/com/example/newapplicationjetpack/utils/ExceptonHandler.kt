package com.example.newapplicationjetpack.utils

import com.example.newapplicationjetpack.data.remote.DTO.response.AppResponse
import com.google.gson.Gson
import retrofit2.HttpException

fun <T> handleHttpException(e: HttpException, errorType: Class<T>): String {
    val errorBody = e.response()?.errorBody()
    return try {
        val appErrorResponse = errorBody?.let {
            Gson().fromJson(it.charStream(), errorType)
        }
        (appErrorResponse as? AppResponse<*>)?.description ?: "Unknown error"
    } catch (jsonParseException: Exception) {
        "Unknown error"
    }
}