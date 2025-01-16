package com.example.newapplicationjetpack.presentation.auth.screens.ForgotPass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplicationjetpack.domain.use_cases.auth.ForgotPassUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPassViewModel @Inject constructor(private val forgotPassUseCase: ForgotPassUseCase) :
    ViewModel() {

    sealed class UiState {
        data object Idl : UiState()
        data object Success : UiState()
        data object Loading : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idl)
    val uiState: StateFlow<UiState> = _uiState

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            forgotPassUseCase(email).collect {
                when(it){
                    is ForgotPassUseCase.Result.Loading->{
                        _uiState.emit(UiState.Loading)
                    }
                    is ForgotPassUseCase.Result.Error->{
                        _uiState.emit(UiState.Error(it.message))
                    }
                    is ForgotPassUseCase.Result.Success->{
                        _uiState.emit(UiState.Success)
                    }
                }
            }
        }
    }
}