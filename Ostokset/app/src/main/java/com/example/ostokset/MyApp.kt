package com.example.ostokset

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class MyScreens {
    Home,
    Summary
}

@Composable
fun MyApp(
    navController: NavHostController = rememberNavController()
) {

    // List to store purchases
    val purchases = remember { mutableStateListOf<Purchase>() }

    Scaffold { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MyScreens.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(MyScreens.Home.name) {
                HomeScreen(
                    onAddPurchase = { purchase ->
                        purchases.add(purchase)
                    },
                    onSummaryClick = {
                        navController.navigate(MyScreens.Summary.name)
                    }
                )
            }

            composable(MyScreens.Summary.name) {
                SummaryScreen(
                    purchases = purchases,
                    onReset = {
                        purchases.clear()
                        navController.navigate(MyScreens.Home.name)
                    }
                )
            }
        }
    }
}