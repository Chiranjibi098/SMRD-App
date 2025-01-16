package com.example.newapplicationjetpack.presentation.auth.screens.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newapplicationjetpack.presentation.components.AppTextField
import com.example.newapplicationjetpack.presentation.components.PasswordTextField
import com.example.newapplicationjetpack.presentation.components.ProgressButton
import com.example.newapplicationjetpack.presentation.auth.screens.utils.LoginValidation


@Composable
fun Login(navController: NavHostController) {

    val viewModel: LoginViewModel = hiltViewModel()

    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val validations = LoginValidation()
    val uiState by viewModel.uiState.collectAsState()

    val currentContext = LocalContext.current
    LaunchedEffect(uiState) {
        if (uiState is LoginViewModel.UiState.Success) {
            navController.navigate("home")
        }
        if (uiState is LoginViewModel.UiState.Error){
            Toast.makeText(currentContext, (uiState as LoginViewModel.UiState.Error).message,Toast.LENGTH_SHORT).show();
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        Text(
            text = "by TATA Power",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight(300)
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppTextField(
            value = email,
            onValueChange = {
                email = it
                val res = validations.validateEmail(email)
                emailError = when (res) {
                    is LoginValidation.ValidationResult.Success -> {
                        ""
                    }
                    is LoginValidation.ValidationResult.Error -> {
                        res.message
                    }
                }
            },
            errorText = emailError,
            placeHolder = "Enter Your Email",
            label = "Email",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Email
        )
        PasswordTextField(
            value = password,
            onValueChange = {
                password = it
                val re = validations.validatePassword(password)
                passwordError = when (re) {
                    is LoginValidation.ValidationResult.Error -> {
                        re.message
                    }
                    is LoginValidation.ValidationResult.Success -> {
                        ""
                    }
                }
            },
            errorText = passwordError,
            placeHolder = "Enter Your Password",
            label = "Password",
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Forgot Password",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("forgotPass") },
            textAlign = TextAlign.Right,
            color = Color(0XFF006BFF)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProgressButton(
                isLoading = uiState is LoginViewModel.UiState.Loading,
                success = uiState is LoginViewModel.UiState.Success,
                buttonText = "Login",
                onClick = {
                    viewModel.login(email, password)
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { navController.navigate("createuser") },
            modifier = Modifier.fillMaxWidth().padding(start = 45.dp, end = 45.dp)
        ) {
            Text(text = "Create User")
        }

    }
}

@Composable
@Preview
private fun Title() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = "Welcome to",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight(400)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "SMRD",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight(600),
            color = Color(0XFF006BFF)
        )
    }
}