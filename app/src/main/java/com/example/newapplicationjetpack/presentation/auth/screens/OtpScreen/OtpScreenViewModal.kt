package com.example.newapplicationjetpack.presentation.auth.screens.OtpScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplicationjetpack.data.remote.DTO.LoginResponse
import com.example.newapplicationjetpack.domain.use_cases.auth.Login2FaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpScreenViewModal @Inject  constructor(private val login2FaUseCase: Login2FaUseCase) : ViewModel() {
    sealed class UiState {
        data object Idle : UiState()
        data object Loading : UiState()
        data class Success(val isLoggedIn: LoginResponse) : UiState()
        data class Error(val message: String) : UiState()
    }
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle);
    val uiState:StateFlow<UiState> = _uiState

    fun verifyOtp(email: String, otp: String) {
        viewModelScope.launch {
            login2FaUseCase(email, otp).collect{
                res-> when(res){
                    is Login2FaUseCase.Result.Loading->{
                        _uiState.emit(UiState.Loading)
                    }
                    is Login2FaUseCase.Result.Success->{
                        _uiState.emit(UiState.Success(res.data))
                    }
                    is Login2FaUseCase.Result.Error->{
                        _uiState.emit(UiState.Error(res.message))
                    }
                }
            }
        }
    }

}