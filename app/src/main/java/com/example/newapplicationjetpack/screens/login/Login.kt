package com.example.newapplicationjetpack.screens.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newapplicationjetpack.R
import com.example.newapplicationjetpack.components.ProgessButton
import com.example.newapplicationjetpack.test


@Composable
fun Login(navController: NavHostController) {

    val viewModel = LoginViewModel(LocalContext.current)
    var username by remember { mutableStateOf("") }
    var isusernameerror by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var ispassworderror by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current;


    Column(modifier = Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(
                text = "Welcome to",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight(400)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "SMRD",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight(600),
                color = Color(0XFF006BFF)
            )
        }
        Text(
            text = "by TATA Power",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight(300)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            OutlinedTextField(
                value = username,
                onValueChange = {
                    val regex = Regex("[a-z0-9]+")
                    username = it
                    if (it.isEmpty()){
                        isusernameerror = "Enter Username";
                    }else if (!regex.matches(it)){
                        isusernameerror = "only lowercase are allowed"
                    }
                    else {
                        isusernameerror = "";
                    }
                },

                placeholder = { Text("Enter your user name") },
                label = { Text("User name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isError = isusernameerror.isNotEmpty(),
                supportingText=(@Composable{
                    Text(text = isusernameerror,
                    )
                })
            )

        }
        OutlinedTextField(
            value = password,
            onValueChange = {
                val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&.]+$")
                password = it
                if (it.isEmpty()) {
                    ispassworderror = "Enter Password"
                } else if (it.length < 8) {
                    ispassworderror = "Password must be at least 8 characters"
                } else if (!regex.matches(it)) {
                    ispassworderror = "Password must include uppercase, lowercase, number, and special character"
                } else {
                    ispassworderror = ""
                }
            },
            placeholder = { Text("Enter your password") },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = if (passwordVisible) painterResource(id = R.drawable.eye_open) else painterResource(id = R.drawable.eye_close),
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            isError = ispassworderror.isNotEmpty(),
            supportingText=(@Composable{
                Text(text = ispassworderror,
                )
            })
        )
        Text(
            text = "Forgot Password",
            modifier = Modifier.fillMaxWidth().clickable { navController.navigate("forgotPass") },
            textAlign = TextAlign.Right,
            color = Color(0XFF006BFF)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                viewModel.login(username, password) { res: Boolean, message: String ->
                   if (res){
                       navController.navigate("otp");
                   }else{
                       Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                   }
                }
            }, modifier = Modifier.fillMaxWidth().padding(start = 45.dp, end = 45.dp)) {
                Text("Login")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {navController.navigate("createuser")},
            modifier = Modifier.fillMaxWidth().padding(start = 45.dp, end = 45.dp)
        ) {
            Text(text = "Create User")
        }

    }
}