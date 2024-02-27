package com.ist.ondemand.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ist.ondemand.presentation.MainViewModel

data class ServiceCard(val id: String, val title: String, val description: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceScreen(navController: NavController, vm: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Service Screen")

        // Sample data for cardList, replace it with your actual data
        val cardList = listOf(
            ServiceCard("1", "Title 1", "Description 1"),
            ServiceCard("2", "Title 2", "Description 2"),
            // Add more items as needed
        )

        // Loop through the services and display them
        cardList.forEach { card ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate("service/${card.id}")
                }
            ) {
                // Display the title and description with custom styles
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    val customTitleStyle = LocalTextStyle.current.copy(
                        fontSize = 20.sp,
                        // Add other custom styling as needed
                    )
                    val customDescriptionStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        // Add other custom styling as needed
                    )

                    Text(text = card.title, style = customTitleStyle)
                    Text(text = card.description, style = customDescriptionStyle)
                }
            }
        }

        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.SERVICES,
            navController = navController
        )
    }
}
