package com.example.newapplicationjetpack.presentation.auth.screens.utils

import android.annotation.SuppressLint
import android.util.Patterns

class LoginValidation {

    sealed class ValidationResult {
        data object Success : ValidationResult()
        data class Error(val message: String) : ValidationResult()
    }

     fun validateEmail(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult.Error("Email cannot be empty")
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> ValidationResult.Error("Invalid email format")
            else -> ValidationResult.Success
        }
    }
 @SuppressLint("SuspiciousIndentation")
 fun validatePassword(password: String): ValidationResult {
     val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&.]+$")
        return when {
            password.isBlank() -> ValidationResult.Error("Password cannot be empty")
            !regex.matches(password)-> ValidationResult.Error("Please Provide a strongpassword")
            else -> ValidationResult.Success
        }
    }



}