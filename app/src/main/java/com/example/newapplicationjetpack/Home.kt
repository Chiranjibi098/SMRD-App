package com.example.newapplicationjetpack

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newapplicationjetpack.screens.UserProfile.UserProfile

data class Contact(
    val name: String,
    val phoneNumber: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactApp() {
    val contacts = remember {
        List(10) { index ->
            Contact(name = "Contact $index", phoneNumber = "123456789$index")
        }
    }

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SMRD") },
                actions = {
                    // Add actions to the top bar if needed
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                items = listOf("Contacts", "Favorites", "Profile"),
                selectedIndex = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> DashboardSmrd(paddingValues)
            1 -> FavoritesScreen(paddingValues)
            2 -> UserProfile()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val icons = listOf(
        painterResource(R.drawable.contacts),
        painterResource(R.drawable.favorite_24dp_d9d9d9_fill0_wght400_grad0_opsz24),
        painterResource(R.drawable.account),
    )
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(painter = icons[index], contentDescription = items[index]) },
                label = { Text(text = item) },
                selected = selectedIndex == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}

@Composable
fun DashboardSmrd(paddingValues: PaddingValues) {
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card (modifier = Modifier.weight(1f).padding(8.dp).height(200.dp).clickable {  }) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally // Align content in the center horizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.noun_meter_7462268),
                            contentDescription = "",
                        )
                        Spacer(modifier = Modifier.height(6.dp)) // Add space between Icon and Text
                        Text(text = "Meter Reading")
                    }
                }
            }
            Card (modifier = Modifier.weight(1f).padding(8.dp).height(200.dp).clickable {  }) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally // Align content in the center horizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bill_dispatch),
                            contentDescription = "",
                        )
                        Spacer(modifier = Modifier.height(6.dp)) // Add space between Icon and Text
                        Text(text = "Bill Dispatch")
                    }
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card (modifier = Modifier.weight(1f).padding(8.dp).height(200.dp).clickable {  }) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally // Align content in the center horizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.noun_billing_6912852),
                            contentDescription = "",
                        )
                        Spacer(modifier = Modifier.height(6.dp)) // Add space between Icon and Text
                        Text(text = "Spot Billing")
                    }
                }
            }
            Card (modifier = Modifier.weight(1f).padding(8.dp).height(200.dp).clickable {  }) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally // Align content in the center horizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.spot_collection),
                            contentDescription = "",
                        )
                        Spacer(modifier = Modifier.height(6.dp)) // Add space between Icon and Text
                        Text(text = "Spot Collection")
                    }
                }
            }
        }
    }
}

@Composable
fun ContactListScreen(contacts: List<Contact>, paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(contacts) { contact ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = contact.phoneNumber, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun FavoritesScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Favorites Screen", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun SettingsScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings Screen", style = MaterialTheme.typography.titleLarge)
    }
}
