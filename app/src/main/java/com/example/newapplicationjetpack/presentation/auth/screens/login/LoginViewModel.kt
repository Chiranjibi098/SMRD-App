package com.example.newapplicationjetpack.presentation.auth.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplicationjetpack.domain.use_cases.auth.LoginUseCase
import com.example.newapplicationjetpack.domain.use_cases.auth.SignupUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase, private val signupUserCase: SignupUserCase) : ViewModel() {

    sealed class UiState {
        data object Idle : UiState()
        data object Loading : UiState()
        data class Success(val isLoggedIn: Boolean) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState


    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(username,password).collect{
                    res-> when(res){
                is LoginUseCase.Result.Loading -> {
                    _uiState.emit(UiState.Loading)
                }
                is LoginUseCase.Result.Success -> {
                    _uiState.emit(UiState.Success(true))
                }
                is LoginUseCase.Result.Error -> {
                    _uiState.emit(UiState.Error(res.message))
                }
            }
            }
        }
    }

}