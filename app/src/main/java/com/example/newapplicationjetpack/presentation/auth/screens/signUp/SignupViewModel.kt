package com.example.newapplicationjetpack.presentation.auth.screens.signUp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplicationjetpack.domain.use_cases.auth.LoginUseCase
import com.example.newapplicationjetpack.domain.use_cases.auth.SignupUserCase
import com.example.newapplicationjetpack.utils.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(context: Context, private val loginUseCase: LoginUseCase, private val signupUserCase: SignupUserCase):ViewModel() {
    private  val preferenceManager = PreferenceManager(context)
    fun createUser(email: String,firstName: String, lastName: String, password: String, onResult: (String) -> Unit) {
        viewModelScope.launch {
            signupUserCase(email,firstName,lastName,password).collect{
                when(it){
                    is SignupUserCase.Result.Success->{
                        preferenceManager.save(it.data.accessToken,"access-token");
                    }
                    is SignupUserCase.Result.Loading->{}
                    is SignupUserCase.Result.Error->{}
                }
            }
        }
    }

}