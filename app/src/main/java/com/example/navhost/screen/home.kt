package com.example.navhost.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


@Composable
fun HomeScreen() {
    val colorList = listOf(Color(0xFF4CAF50), Color(0xFFFF9800), Color(0xFF2196F3), Color(0xFF00BCD4)) // Green
    var colorIndex by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        ElevatedCard(
            colors = CardDefaults.elevatedCardColors(containerColor = colorList[colorIndex]),
            modifier = Modifier
                .size(300.dp, 250.dp)
                .clickable {
                    colorIndex = (colorIndex + 1) % colorList.size // পরের কালারে যাবে, শেষে গেলে আবার শুরু থেকে
                }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Click to change: ${colorIndex + 1}/${colorList.size}")
            }
        }
    }
}





