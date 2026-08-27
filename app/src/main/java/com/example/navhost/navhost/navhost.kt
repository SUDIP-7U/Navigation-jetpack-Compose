package com.example.navhost.navhost

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navhost.screen.HomeScreen
import com.example.navhost.screen.NotificationScreen

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object Notification : Screen("notification", "Notification", Icons.Filled.Notifications)
}
val bottomNavItems = listOf(Screen.Home, Screen.Notification)
@Composable
fun AppRoot() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomBar(navController) }
    ) { innerPadding ->
        // NavHost Logics
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Notification.route) { NotificationScreen() }
        }

    }
}