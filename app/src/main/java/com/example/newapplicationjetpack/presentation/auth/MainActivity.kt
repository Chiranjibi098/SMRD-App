package com.example.newapplicationjetpack.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newapplicationjetpack.utils.PreferenceManager
import com.example.newapplicationjetpack.presentation.dashboard.DashboardActivity
import com.example.newapplicationjetpack.presentation.dashboard.screens.ContactApp
import com.example.newapplicationjetpack.presentation.auth.screens.ForgotPass.ForgotPass
import com.example.newapplicationjetpack.presentation.auth.screens.OtpScreen.OtpScreen
import com.example.newapplicationjetpack.presentation.auth.screens.ResetPass.ResetPass
import com.example.newapplicationjetpack.presentation.auth.screens.signUp.CreateUser
import com.example.newapplicationjetpack.presentation.auth.screens.login.Login
import com.example.newapplicationjetpack.presentation.auth.screens.splashscreen.SplashScreen
import com.example.newapplicationjetpack.presentation.auth.theme.NewApplicationJetpackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val data = intent.data
        var resetpassToken:String? = null
        val preferenceManager = PreferenceManager(context = this)
        if (!preferenceManager.get("accessToken","").equals("")){
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent);

        }
        setContent {
            NewApplicationJetpackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()){ innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "splashscreen") {
                        composable("login") { Login(navController) }
                        composable("test") { ContactApp() }
                        composable("splashscreen") { SplashScreen(navController) }
                        composable("createuser") { CreateUser() }
                        composable("otp") { OtpScreen() }
                        composable("forgotPass") { ForgotPass() }
                        composable("resetPass") { resetpassToken?.let { it1 -> ResetPass(it1) } }
                    }
                    if(data!=null){
                        val pathSegments = data.pathSegments
                        if(pathSegments.size==2){
                            if (pathSegments[0].equals("reset-password")){
                                resetpassToken = pathSegments[1];
                                navController.navigate("resetPass")
                            }
                        }
                    }
                }
            }
        }
    }
}
