package com.example.newapplicationjetpack.presentation.auth.screens.ForgotPass

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newapplicationjetpack.presentation.components.ProgressButton

@Composable
fun ForgotPass() {

    val viewModel: ForgotPassViewModel = hiltViewModel()
    var email by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current;

    LaunchedEffect(uiState) {
        if (uiState is ForgotPassViewModel.UiState.Success) {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter your email",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProgressButton(
            isLoading = uiState is ForgotPassViewModel.UiState.Loading,
            success = uiState is ForgotPassViewModel.UiState.Success,
            onClick = {
                viewModel.forgotPassword(email)
            },
            buttonText = "Send email"
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (uiState is ForgotPassViewModel.UiState.Error) {
            Text(
                text = "Error: ${(uiState as ForgotPassViewModel.UiState.Error).message}",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
