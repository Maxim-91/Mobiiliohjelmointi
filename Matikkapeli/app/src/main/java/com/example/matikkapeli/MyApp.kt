package com.example.matikkapeli

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import kotlinx.coroutines.launch

// Enum for route names to avoid typos
enum class MathScreen {
    Start, Game, Result, Rules, About
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Track current screen for UI logic
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: MathScreen.Start.name

    // Shared state for the game session
    var isAddition by remember { mutableStateOf(true) }
    var finalScore by remember { mutableStateOf(0) }

    // Navigation Drawer setup
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = currentRoute == MathScreen.Start.name,
                    onClick = { navController.navigate(MathScreen.Start.name); scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Rules") },
                    selected = currentRoute == MathScreen.Rules.name,
                    onClick = { navController.navigate(MathScreen.Rules.name); scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("About") },
                    selected = currentRoute == MathScreen.About.name,
                    onClick = { navController.navigate(MathScreen.About.name); scope.launch { drawerState.close() } }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Matikkapeli") },
                    // Back button logic: show back arrow if possible, else show drawer menu icon
                    navigationIcon = {
                        if (navController.previousBackStackEntry != null) {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        } else {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    }
                )
            },
            bottomBar = {
                // Navigation bar for Rules and About
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Info, null) },
                        label = { Text("Rules") },
                        selected = currentRoute == MathScreen.Rules.name,
                        onClick = { navController.navigate(MathScreen.Rules.name) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, null) },
                        label = { Text("About") },
                        selected = currentRoute == MathScreen.About.name,
                        onClick = { navController.navigate(MathScreen.About.name) }
                    )
                }
            }
        ) { innerPadding ->
            // Host for all screens
            NavHost(
                navController = navController,
                startDestination = MathScreen.Start.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(MathScreen.Start.name) {
                    StartScreen(onModeSelected = { addition ->
                        isAddition = addition
                        navController.navigate(MathScreen.Game.name)
                    })
                }
                composable(MathScreen.Game.name) {
                    GameScreen(
                        isAddition = isAddition,
                        onGameEnd = { score ->
                            finalScore = score
                            navController.navigate(MathScreen.Result.name)
                        }
                    )
                }
                composable(MathScreen.Result.name) {
                    ResultScreen(score = finalScore, onRestart = {
                        navController.navigate(MathScreen.Start.name) {
                            popUpTo(MathScreen.Start.name) { inclusive = true }
                        }
                    })
                }
                composable(MathScreen.Rules.name) { RulesScreen() }
                composable(MathScreen.About.name) { AboutScreen() }
            }
        }
    }
}