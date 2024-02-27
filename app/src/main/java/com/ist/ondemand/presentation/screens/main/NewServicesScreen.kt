package com.ist.ondemand.presentation.screens.main


import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ist.ondemand.presentation.MainViewModel
import com.ist.ondemand.presentation.common.ProgressSpinner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewServiesScreen(navController: NavController, vm: MainViewModel, encodedUri: String) {

    val imageUri by remember { mutableStateOf(encodedUri) }
    var description by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Cancel", modifier = Modifier.clickable { navController.popBackStack() })
            Text(text = "Save", modifier = Modifier.clickable {
                focusManager.clearFocus()
                vm.onNewService(Uri.parse(imageUri), description) { navController.popBackStack() }
            })
        }



        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 150.dp),
            contentScale = ContentScale.FillWidth
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "New Service")

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

    val inProgress = vm.inProgress.value
    if (inProgress)
        ProgressSpinner()

}