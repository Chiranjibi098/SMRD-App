package com.example.newapplicationjetpack.presentation.auth.screens.OtpScreen

import android.text.TextUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.composeuisuite.ohteepee.OhTeePeeInput
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
import com.example.newapplicationjetpack.utils.PreferenceManager
import com.example.newapplicationjetpack.presentation.components.ProgressButton

@Composable
fun OtpScreen() {

    val viewModel: OtpScreenViewModal = hiltViewModel();
    val uiState by viewModel.uiState.collectAsState();

    val context = LocalContext.current
    val preferenceManager = PreferenceManager(context)

    var otpInput by remember { mutableStateOf("") }

    val defaultCellConfig = OhTeePeeCellConfiguration.withDefaults(
        borderColor = Color.LightGray,
        borderWidth = 1.dp,
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle(
            color = Color.Black
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter your OTP")
        Spacer(modifier = Modifier.height(20.dp))

        // OTP Input Field
        OhTeePeeInput(
            value = otpInput,
            onValueChange = { newValue, isValid ->
                otpInput = newValue
            },
            configurations = OhTeePeeConfigurations.withDefaults(
                cellsCount = 6,
                emptyCellConfig = defaultCellConfig,
                cellModifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(48.dp),
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Button to Send/Verify OTP
        ProgressButton(
            isLoading = uiState is OtpScreenViewModal.UiState.Loading,
            success = uiState is OtpScreenViewModal.UiState.Success,
            onClick = {
                val email = preferenceManager.get("loginEmail", "")
                if (!TextUtils.isEmpty(email)) {
                    if (otpInput.isNotBlank()) {
                        viewModel.verifyOtp(email!!, otpInput)
                    }
                }

            },
            buttonText = if (otpInput.isBlank()) "Send OTP" else "Verify OTP"
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (uiState is OtpScreenViewModal.UiState.Error) {
            Text(
                text = "Error: ${(uiState as OtpScreenViewModal.UiState.Error).message}",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
