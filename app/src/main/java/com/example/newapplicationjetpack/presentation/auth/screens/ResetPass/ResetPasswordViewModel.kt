package com.example.newapplicationjetpack.presentation.auth.screens.ResetPass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplicationjetpack.data.remote.DTO.response.AppResponse
import com.example.newapplicationjetpack.data.remote.DTO.request.ResetPasswordRequest
import com.example.newapplicationjetpack.domain.services.AuthService
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {

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
                val res = authService.resetPassword(ResetPasswordRequest(token, newpass))
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