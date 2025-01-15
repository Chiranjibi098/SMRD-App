package com.example.newapplicationjetpack.screens.ForgotPass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplicationjetpack.DTO.AppErrorResponse
import com.example.newapplicationjetpack.DTO.AppResponse
import com.example.newapplicationjetpack.DTO.ForgotPasswordRequest
import com.example.newapplicationjetpack.services.AuthService
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
class ForgotPassViewModel : ViewModel() {
    private val authService = AuthService()

    // Use StateFlow for state management
    private val _isEmailSent = MutableLiveData(false)
    val isEmailSent: LiveData<Boolean> = _isEmailSent

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val forgotPasswordResp = authService.forgotPassword(ForgotPasswordRequest(email))
                _errorMessage.value = null
                _isEmailSent.value = forgotPasswordResp.data
                kotlinx.coroutines.delay(2000)
                _isEmailSent.value = false
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
            }finally {
                _isLoading.value =false
            }
        }
    }
}