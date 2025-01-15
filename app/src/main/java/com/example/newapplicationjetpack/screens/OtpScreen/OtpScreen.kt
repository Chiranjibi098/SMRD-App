package com.example.newapplicationjetpack.screens.OtpScreen

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.OhTeePeeInput
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
import com.example.newapplicationjetpack.PreferenceManager
import com.example.newapplicationjetpack.activities.DashboardActivity
import com.example.newapplicationjetpack.components.ProgessButton

@Composable
fun OtpScreen(viewModel: OtpScreenViewModal, context: Context) {
    val res by viewModel.successres.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()
    val preferenceManager = PreferenceManager(context)

    var otpInput by remember { mutableStateOf("") }

    // State to store OTP entered by the user
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
        ProgessButton(
            isLoading = (isLoading == true),
            success = res != null,
            onClick = {
                val email = preferenceManager.get("loginEmail", "")
                if (!TextUtils.isEmpty(email)) {
                    if (otpInput.isNotBlank()) {
                        viewModel.verifyOtp(email!!, otpInput){
                            res?.let { preferenceManager.save(it.accessToken,"accessToken") }
                            val intent = Intent(context,DashboardActivity::class.java);
                            context.startActivity(intent)
                            (context as Activity).finishAffinity()
                        }
                    }
                }

            },
            buttonText = if (otpInput.isBlank()) "Send OTP" else "Verify OTP"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display success message
        if (res != null) {
            Text(
                text = if (otpInput.isBlank()) "OTP sent successfully!" else "OTP verified successfully!",
                style = MaterialTheme.typography.bodyMedium
            )

        }

        // Display error message if any
        if (!TextUtils.isEmpty(errorMessage)) {
            Text(
                text = "Error: $errorMessage",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
