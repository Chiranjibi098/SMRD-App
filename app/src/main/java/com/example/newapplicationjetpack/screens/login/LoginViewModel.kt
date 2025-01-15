package com.example.newapplicationjetpack.screens.login

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplicationjetpack.DTO.AppErrorResponse
import com.example.newapplicationjetpack.DTO.SignupRequest
import com.example.newapplicationjetpack.PreferenceManager
import com.example.newapplicationjetpack.services.AuthService
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(context:Context) : ViewModel() {


    private val repository = AuthService()

    private  val preferenceManager = PreferenceManager(context)
    private var loginResult: Boolean? = null

    fun login(username: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                loginResult = response.data
                preferenceManager.save(username,"loginEmail")
                onResult(loginResult!!, response.description!!)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                val appErrorResponse = try {
                    // Ensure errorBody is parsed safely
                    errorBody?.let { Gson().fromJson(it.charStream(), AppErrorResponse::class.java) }
                } catch (jsonParseException: Exception) {
                    // If parsing fails, return a generic error message
                    null
                }

                val errorMessage = appErrorResponse?.description ?: "Unknown error"
                onResult(false, errorMessage)
            }catch (e: Exception) {
                onResult(false,"Error: ${e.message}")
            }
        }
    }
    fun createUser(email: String,firstName: String, lastName: String, password: String, onResult: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val requestBody = SignupRequest(email, password, firstName, lastName, "Admin")
                val response = repository.createUser(requestBody)
                onResult("Success: ${response.refreshToken}")
            } catch (e: Exception) {
                onResult("Error: ${e.message}")
            }
        }
    }

}