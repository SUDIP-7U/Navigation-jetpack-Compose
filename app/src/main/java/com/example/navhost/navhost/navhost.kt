package com.example.navhost.navhost


import androidx.compose.material.icons.filled.Settings
import com.example.navhost.screen.HomeScreen
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
import com.example.navhost.screen.NotificationScreen
import com.example.navhost.screen.SettingScreen


sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object Notification : Screen("notification", "Notification", Icons.Filled.Notifications)
    data object Setting : Screen("setting", "Setting", Icons.Filled.Settings)
}
val bottomNavItems = listOf(Screen.Home, Screen.Notification, Screen.Setting)
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
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Notification.route) { NotificationScreen() }
            composable(Screen.Setting.route) { SettingScreen() }
        }

    }
}
