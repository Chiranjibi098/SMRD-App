package com.example.newapplicationjetpack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun State(navController: NavHostController) {
    val context = LocalContext.current

    val preferenceManager = remember { PreferenceManager(context) }

    var count by rememberSaveable {
        mutableStateOf(preferenceManager.get("count", "0")?.toInt() ?: 0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "State: $count", fontSize = 14.sp)

        Button(onClick = {
//            count += 1 // Increment count
//            preferenceManager.save(count.toString(), "count") // Save the updated count
            navController.navigate("test")
        }) {
            Text(text = "Increment")
        }
    }
}
