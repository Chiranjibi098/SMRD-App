package com.example.newapplicationjetpack.screens.splashscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize(),
            color = Color(0xFF001A6E)
        ) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SMRD Application",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight(500)
            )
            Text(
                text = "by Tata Power",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight(200)
            )
        }
    }
    LaunchedEffect(Unit) {
        delay(1000) // 3-second delay
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }
}