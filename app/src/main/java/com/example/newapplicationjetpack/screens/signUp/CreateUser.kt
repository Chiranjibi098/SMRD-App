package com.example.newapplicationjetpack.screens.signUp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.newapplicationjetpack.R
import com.example.newapplicationjetpack.screens.login.LoginViewModel

@Composable
fun CreateUser(modifier: Modifier = Modifier) {
    val viewModel = LoginViewModel(LocalContext.current)

    var username by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isusernameerror by remember { mutableStateOf("") }
    var ispassworderror by remember { mutableStateOf("") }
    var isroleerror by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmpasswordVisible by remember { mutableStateOf(false) }
    var confirmpassword by remember { mutableStateOf("") }
    var isconfirmpassworderror by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isemailerror by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create user",
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = {
                val regex = Regex("[a-z0-9]+")
                username = it
                if (it.isEmpty()) {
                    isusernameerror = "Enter Username";
                } else if (!regex.matches(it)) {
                    isusernameerror = "only lowercase are allowed"
                } else {
                    isusernameerror = "";
                }
            },
            placeholder = { Text("Enter your user name") },
            label = { Text("User name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = isusernameerror.isNotEmpty(),
            supportingText = (@Composable {
                Text(
                    text = isusernameerror,
                )
            })
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = role,
            onValueChange = {
                val regex = Regex("[a-z0-9]+")
                role = it
                if (it.isEmpty()) {
                    isroleerror = "Enter your Role";
                } else if (!regex.matches(it)) {
                    isroleerror = "only lowercase are allowed"
                } else {
                    isroleerror = "";
                }
            },
            placeholder = { Text("Enter your role") },
            label = { Text("role") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = isroleerror.isNotEmpty(),
            supportingText = (@Composable {
                Text(
                    text = isroleerror,
                )
            })
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()
                email = it
                if (it.isEmpty()) {
                    isemailerror = "Enter your Email";
                } else if (!emailRegex.matches(it)) {
                    isemailerror = "Email format not correct"
                } else {
                    isemailerror = "";
                }
            },
            placeholder = { Text("Enter your email") },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = isemailerror.isNotEmpty(),
            supportingText = (@Composable {
                Text(
                    text = isemailerror,
                )
            })
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                val regex =
                    Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&.]+$")
                password = it
                if (it.isEmpty()) {
                    ispassworderror = "Enter Password"
                } else if (it.length < 8) {
                    ispassworderror = "Password must be at least 8 characters"
                } else if (!regex.matches(it)) {
                    ispassworderror =
                        "Password must include uppercase, lowercase, number, and special character"
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
                        painter = if (passwordVisible) painterResource(id = R.drawable.eye_open) else painterResource(
                            id = R.drawable.eye_close
                        ),
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            isError = ispassworderror.isNotEmpty(),
            supportingText = (@Composable {
                Text(
                    text = ispassworderror,
                )
            })
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = confirmpassword,
            onValueChange = {
                val regex =
                    Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&.]+$")
                confirmpassword = it
                isconfirmpassworderror = if (it.isEmpty()) {
                    "Confirm your Password"
                } else if (it != password) {
                    "Password did not matched"
                } else if (it.length < 8) {
                    "Password must be at least 8 characters"
                } else if (!regex.matches(it)) {
                    "Password must include uppercase, lowercase, number, and special character"
                } else {
                    ""
                }
            },
            placeholder = { Text("Confirm your password") },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (confirmpasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { confirmpasswordVisible = !confirmpasswordVisible }) {
                    Icon(
                        painter = if (confirmpasswordVisible) painterResource(id = R.drawable.eye_open) else painterResource(
                            id = R.drawable.eye_close
                        ),
                        contentDescription = if (confirmpasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            isError = isconfirmpassworderror.isNotEmpty(),
            supportingText = (@Composable {
                Text(
                    text = isconfirmpassworderror,
                )
            })

        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                viewModel.createUser(email, "Chiranjibi", "Sahu", password) { result ->
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Create User")
            }
        }
    }
}