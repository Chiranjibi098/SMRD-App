package com.example.newapplicationjetpack.screens.ResetPass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplicationjetpack.DTO.AppResponse
import com.example.newapplicationjetpack.DTO.ResetPasswordRequest
import com.example.newapplicationjetpack.services.AuthService
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ResetPasswordViewModel : ViewModel() {

    private val authService = AuthService()
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _success = MutableLiveData(false)
    val success: LiveData<Boolean> = _success

    private val _errormessage:MutableLiveData<String?> = MutableLiveData(null)
    val errormessage: LiveData<String?> = _errormessage

    fun resetpassword(token: String, newpass: String){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val res = authService.restPassword(ResetPasswordRequest(token, newpass))
                _errormessage.value = null
                _success.value = res.data
                kotlinx.coroutines.delay(2000)
                _success.value = false
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                val appErrorResponse = try {
                    errorBody?.let { Gson().fromJson(it.charStream(), AppResponse::class.java) }
                } catch (jsonParseException: Exception) {
                    null
                }
                val errorMessage = appErrorResponse?.description ?: "Unknown error"
                _errormessage.value = errorMessage
            } catch (e: Exception) {
                _isLoading.value = false
                _errormessage.value = e.message
            }finally {
                _isLoading.value =false
            }
        }
    }

}