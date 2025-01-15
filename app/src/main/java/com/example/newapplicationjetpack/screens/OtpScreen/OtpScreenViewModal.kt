package com.example.newapplicationjetpack.screens.OtpScreen

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplicationjetpack.DTO.AppResponse
import com.example.newapplicationjetpack.DTO.ForgotPasswordRequest
import com.example.newapplicationjetpack.DTO.LoginResponse
import com.example.newapplicationjetpack.DTO.TwoStepReq
import com.example.newapplicationjetpack.services.AuthService
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OtpScreenViewModal : ViewModel() {
    private val authService = AuthService()

    private val _successres: MutableLiveData<LoginResponse?> = MutableLiveData(null)
    val successres: MutableLiveData<LoginResponse?> = _successres

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage
    fun verifyOtp(email: String, otp: String,onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val tokenres = authService.twoStepVerify(TwoStepReq(email, otp))
                _errorMessage.value = null
                _successres.value = tokenres.data
                onSuccess()
                kotlinx.coroutines.delay(2000)
                _successres.value = null
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                val appErrorResponse = try {
                    errorBody?.let { Gson().fromJson(it.charStream(), AppResponse::class.java) }
                } catch (jsonParseException: Exception) {
                    null
                }
                val errorMessage = appErrorResponse?.description ?: "Unknown error"
                _errorMessage.value = errorMessage
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

}