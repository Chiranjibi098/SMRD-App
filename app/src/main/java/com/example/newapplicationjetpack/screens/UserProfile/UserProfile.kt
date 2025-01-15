package com.example.newapplicationjetpack.screens.UserProfile

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newapplicationjetpack.R

@Preview(showBackground = true, showSystemUi = true,)
@Composable
fun UserProfile(modifier: Modifier = Modifier) {
    Row (modifier = Modifier.fillMaxWidth()) {
        Column (modifier =  Modifier.fillMaxWidth().padding(vertical = 110.dp, horizontal = 20.dp),) {
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id= R.drawable.newlogo),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = modifier.size(65.dp).clip(RoundedCornerShape(35.dp)),
                )
                Text(text = "Welcome user!",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(R.drawable.call),
                    contentDescription = ("")
                )
                Text(text = "User number",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(R.drawable.mail),
                    contentDescription = ("")
                )
                Text(text = "User email",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(R.drawable.settings_24dp_d9d9d9_fill0_wght400_grad0_opsz24),
                    contentDescription = ("")
                )
                Text(text = "Settings",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {}, modifier = Modifier.padding(horizontal = 0.dp)) {
                    Icon(painter = painterResource(R.drawable.logout),
                        contentDescription = ("")
                    )
                    Text(text = "logout")
                }
            }

        }
    }
}