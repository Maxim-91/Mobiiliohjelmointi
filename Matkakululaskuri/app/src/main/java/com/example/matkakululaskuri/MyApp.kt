package com.example.matkakululaskuri

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.NavHostController

// Navigation routes used in the app
enum class ExpenseScreens {
    Home,
    PerDiem,
    Mileage,
    Summary
}

@Composable
fun MyApp(
    navController: NavHostController = rememberNavController()
) {

    // These states store accumulated expenses during app usage
    var perDiemTotal by remember { mutableStateOf(0.0) }
    var mileageTotal by remember { mutableStateOf(0.0) }

    Scaffold { innerPadding ->

        // NavHost controls navigation between screens
        NavHost(
            navController = navController,
            startDestination = ExpenseScreens.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            // Home screen
            composable(ExpenseScreens.Home.name) {
                HomeScreen(
                    onPerDiemClick = {
                        navController.navigate(ExpenseScreens.PerDiem.name)
                    },
                    onMileageClick = {
                        navController.navigate(ExpenseScreens.Mileage.name)
                    },
                    onSummaryClick = {
                        navController.navigate(ExpenseScreens.Summary.name)
                    }
                )
            }

            // Per diem input screen
            composable(ExpenseScreens.PerDiem.name) {
                PerDiemScreen(
                    onSave = { days, amountPerDay ->
                        // Calculate and accumulate per diem expenses
                        perDiemTotal += days * amountPerDay
                        navController.popBackStack() // Return to Home
                    }
                )
            }

            // Mileage input screen
            composable(ExpenseScreens.Mileage.name) {
                MileageScreen(
                    onSave = { kilometers ->
                        // 0.50 € per kilometer as required
                        mileageTotal += kilometers * 0.5
                        navController.popBackStack() // Return to Home
                    }
                )
            }

            // Summary screen
            composable(ExpenseScreens.Summary.name) {
                SummaryScreen(
                    perDiemTotal = perDiemTotal,
                    mileageTotal = mileageTotal,
                    onContinue = {
                        // Return to Home without resetting values
                        navController.popBackStack()
                    },
                    onReset = {
                        // Reset all accumulated values
                        perDiemTotal = 0.0
                        mileageTotal = 0.0

                        // Clear back stack and return to Home
                        navController.navigate(ExpenseScreens.Home.name) {
                            popUpTo(0)
                        }
                    }
                )
            }
        }
    }
}