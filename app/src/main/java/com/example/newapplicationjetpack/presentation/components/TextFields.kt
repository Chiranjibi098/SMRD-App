package com.example.newapplicationjetpack.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.newapplicationjetpack.R

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    errorText: String,
    placeHolder:String?,
    label:String?,
    modifier:Modifier,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            if (placeHolder != null) {
                Text(placeHolder)
            }
        },
        label = {
            if (label != null) {
                Text(label)
            }
        },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = errorText.isNotBlank(),
        supportingText = {
            Text(text = errorText)
        }
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    errorText: String,
    placeHolder: String?,
    label: String?,
    modifier: Modifier = Modifier,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            if (placeHolder != null) {
                Text(placeHolder)
            }
        },
        label = {
            if (label != null) {
                Text(label)
            }
        },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = errorText.isNotBlank(),
        supportingText = {
            Text(text = errorText)
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = if (passwordVisible) painterResource(id = R.drawable.eye_open) else painterResource(id = R.drawable.eye_close),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        }
    )
}