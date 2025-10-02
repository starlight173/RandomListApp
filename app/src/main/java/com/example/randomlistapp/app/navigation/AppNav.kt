package com.example.randomlistapp.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.randomlistapp.features.items.navigation.ItemsDestinations
import com.example.randomlistapp.features.items.navigation.itemsNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNav(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route

    Scaffold (
        topBar = {
            when (currentDestination) {
                ItemsDestinations.List -> {
                    TopAppBar(title = { Text("Random List App") })
                }
                ItemsDestinations.Detail -> {
                    TopAppBar(
                        title = { Text("Item Detail") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )
                }
                else -> { /* optional for other screens */ }
            }
        },
        bottomBar = {
            // You could add BottomNavigation here if needed
        },
        floatingActionButton = {
            // FloatingActionButton(onClick = { /* TODO */ }) { Icon(Icons.Default.Add, null) }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ItemsDestinations.Graph,
            modifier = modifier.padding(innerPadding)
        ) {
            itemsNavGraph(navControllerProvider = { navController })
        }
    }
}