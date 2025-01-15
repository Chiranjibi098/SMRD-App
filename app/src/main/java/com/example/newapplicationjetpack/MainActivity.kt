package com.example.newapplicationjetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newapplicationjetpack.activities.DashboardActivity
import com.example.newapplicationjetpack.screens.ForgotPass.ForgotPass
import com.example.newapplicationjetpack.screens.ForgotPass.ForgotPassViewModel
import com.example.newapplicationjetpack.screens.OtpScreen.OtpScreen
import com.example.newapplicationjetpack.screens.OtpScreen.OtpScreenViewModal
import com.example.newapplicationjetpack.screens.ResetPass.ResetPass
import com.example.newapplicationjetpack.screens.ResetPass.ResetPasswordViewModel
import com.example.newapplicationjetpack.screens.signUp.CreateUser
import com.example.newapplicationjetpack.screens.login.Login
import com.example.newapplicationjetpack.screens.splashscreen.SplashScreen
import com.example.newapplicationjetpack.ui.theme.NewApplicationJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val model = ForgotPassViewModel()
        val otpScreenViewModal = OtpScreenViewModal()
        val data = intent.data
        var resetpassToken:String? = null
        val resetmodel = ResetPasswordViewModel()
        val preferenceManager = PreferenceManager(context = this)
        if (!preferenceManager.get("accessToken","").equals("")){
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent);

        }
        setContent {
            NewApplicationJetpackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()){ innerPadding ->
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "test") {
                        composable("login") { Login(navController) }
                        composable("home") { State(navController) }
                        composable("test") { ContactApp() }
                        composable("splashscreen") { SplashScreen(navController) }
                        composable("createuser") { CreateUser() }
                        composable("otp") { OtpScreen(otpScreenViewModal, LocalContext.current) }
                        composable("forgotPass") { ForgotPass(Modifier,model) }
                        composable("resetPass") { ResetPass(resetmodel,resetpassToken) }
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun test(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Card (
            modifier= modifier.fillMaxWidth().padding(all = 16.dp),

            ) {
            Column(
                modifier = modifier.fillMaxWidth().padding(all = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(text = "Hello World", fontSize = 28.sp, fontWeight = FontWeight.Bold )
                Spacer(modifier = Modifier.height(16.dp))
                Image(painter = painterResource(id= R.drawable.newlogo),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = modifier.size(100.dp).clip(RoundedCornerShape(4.dp)),
                )
                Spacer(modifier = modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)){
                    Button(onClick = {
                    },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Add")
                    }
                    Button(onClick = {/*TODO*/},
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Sub")
                    }
                }
            }
        }
    }

}