package com.example.newapplicationjetpack.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgessButton(
    isLoading: Boolean,
    success: Boolean,
    onClick: () -> Unit,
    buttonText: String = "Reset password",
) {
    Button(
        onClick = onClick,
        enabled = !isLoading && !success
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.width(36.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        } else {
            Text(text = buttonText)
        }
    }
}