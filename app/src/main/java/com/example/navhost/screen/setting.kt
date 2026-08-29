package com.example.navhost.screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen() {
    val view = LocalView.current
    if (!view.isInEditMode) {
        DisposableEffect(view) {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)
            
            // Setting screen এর জন্য স্ট্যাটাস বার নীল এবং আইকন সাদা করছি
            insetsController.isAppearanceLightStatusBars = false
            
            onDispose {
                // empty: রেস কন্ডিশন এড়াতে এখানে জোর করে রিসেট করছি না
            }
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0), // Fix: ডাবল বটম প্যাডিং বন্ধ করবে
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Settings", 
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3) // Blue color
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE3F2FD)) // Light blue background
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Setting Screen Content")
        }
    }
}
