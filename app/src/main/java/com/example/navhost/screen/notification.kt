package com.example.navhost.screen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.navhost.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {
    val view = LocalView.current
    if (!view.isInEditMode) {
        DisposableEffect(view) {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)
            
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
                        text = "Notifications", fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Handle settings */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF186318)
                )
            )
        }
    ) { innerPadding ->
        PreviewRowData(innerPadding)
    }
}

@Composable
fun RowData(logo: Int, data: String) {
    Card(modifier = Modifier.padding(10.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Image(painter = painterResource(id = logo),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
                    .clip(RoundedCornerShape(14.dp))
            )
            Text(text = data, fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp).weight(1f)
            )
        }
    }
}

@Composable
fun PreviewRowData(innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0E0))
            .padding(innerPadding)
    ) {
        items(myLanguage()) { item ->
            RowData(logo = item.image, data = item.text)
        }
    }
}

data class Language(val image: Int, val text: String)

fun myLanguage(): List<Language> {
    return listOf(
        Language(R.drawable.apple, "Apple"),
        Language(R.drawable.java, "Core Java"),
        Language(R.drawable.javascipt, "JavaScript"),
        Language(R.drawable.lg, "LG Smart TV"),
        Language(R.drawable.hp, "Hp Laptop"),
        Language(R.drawable.asus, "Asus"),
        Language(R.drawable.lenovo, "Lenovo"),
        Language(R.drawable.asus, "Asus"),
        Language(R.drawable.casio, "CASIO"),
        Language(R.drawable.logo, "Kotlin Multiplatform")

    )
}