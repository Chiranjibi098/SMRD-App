package com.example.newapplicationjetpack.screens.ForgotPass

import android.text.TextUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newapplicationjetpack.components.ProgessButton

@Composable
fun ForgotPass(modifier: Modifier = Modifier,viewModel: ForgotPassViewModel) {


    var email by remember { mutableStateOf("") }
    val isEmailSent by viewModel.isEmailSent.observeAsState() // Collect the state from the ViewModel
    val isLoading by viewModel.isLoading.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()

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
       ProgessButton(
           isLoading = isLoading == true,
           success = isEmailSent == true,
           onClick = {
               viewModel.forgotPassword(email)
           },
           buttonText = "Send email"

       )
        Spacer(modifier = Modifier.height(16.dp))

        if (isEmailSent==true) {
            Text(
                text = "Email sent successfully!",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Show error message if there's an error
        if (!TextUtils.isEmpty(errorMessage)) {
            Text(
                text = "Error: $errorMessage",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
