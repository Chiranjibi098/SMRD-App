package com.example.newapplicationjetpack.screens.ResetPass

import android.text.TextUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResetPass(viewModel: ResetPasswordViewModel, resetpassToken: String?) {
    var resetpass by remember() { mutableStateOf("") }
    val success by viewModel.success.observeAsState() // Collect the state from the ViewModel
    val isLoading by viewModel.isLoading.observeAsState()
    val errorMessage by viewModel.errormessage.observeAsState()
    Column (modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "Reset password",
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            value = resetpass,
            onValueChange = {resetpass = it},
            label = { Text("Password") },
            placeholder = { Text("Enter new password") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (resetpassToken != null) {
                viewModel.resetpassword(resetpassToken, resetpass)
            }
        }, enabled = isLoading == false && success == false) {
            if (isLoading==true) {
                CircularProgressIndicator(
                    modifier = Modifier.width(36.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else {
                Text(text = "Reset password")
            }
        }

        if (success==true) {
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