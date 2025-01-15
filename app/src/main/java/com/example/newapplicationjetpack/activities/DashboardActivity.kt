package com.example.newapplicationjetpack.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newapplicationjetpack.ContactApp
import com.example.newapplicationjetpack.State
import com.example.newapplicationjetpack.activities.ui.theme.NewApplicationJetpackTheme
import com.example.newapplicationjetpack.screens.ForgotPass.ForgotPass
import com.example.newapplicationjetpack.screens.OtpScreen.OtpScreen
import com.example.newapplicationjetpack.screens.ResetPass.ResetPass
import com.example.newapplicationjetpack.screens.login.Login
import com.example.newapplicationjetpack.screens.signUp.CreateUser
import com.example.newapplicationjetpack.screens.splashscreen.SplashScreen

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewApplicationJetpackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "test") {
                        composable("test") { ContactApp() }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewApplicationJetpackTheme {
        Greeting("Android")
    }
}