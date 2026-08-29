package com.example.navhost.screen

import android.app.Activity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val view = LocalView.current
    if (!view.isInEditMode) {
        DisposableEffect(view) {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)
            
            // বেগুনী (Purple) ব্যাকগ্রাউন্ডের জন্য আইকন সাদা (AppearanceLight = false)
            insetsController.isAppearanceLightStatusBars = false
            
            onDispose {
                // empty
            }
        }
    }

    var running by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }

    // Smooth animation when progress value changes
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    val colorList = listOf(
        Color(0xFF4CAF50), // Green
        Color(0xFFFF9800), // Orange
        Color(0xFF2196F3), // Blue
        Color(0xFF00BCD4)  // Cyan
    )
    var colorIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(running) {
        if (running) {
            while (progress < 1f && running) {
                delay(70.milliseconds)
                progress = (progress + 0.02f).coerceAtMost(1f)
            }
            running = false
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0), // Fix: ডাবল বটম প্যাডিং বন্ধ করবে
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Home", 
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF9C27B0) // বেগুনী (Purple) কালার
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp)
                    .background(color = Color(0xFF648EBD), shape = RoundedCornerShape(14.dp))
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(14.dp)),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 🔹 Upper half: Progress bar + controls
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f).fillMaxWidth()
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(120.dp)) {
                        CircularProgressIndicator(
                            progress = { animatedProgress },
                            modifier = Modifier.fillMaxSize(),
                            color = ProgressIndicatorDefaults.circularColor,
                            strokeWidth = 8.dp,
                            trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
                            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                        )
                        Text(
                            text = "${(animatedProgress * 100).toInt()}%",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row {
                        Button(
                            enabled = !running,
                            onClick = {
                                if (progress >= 1f) progress = 0f
                                running = true
                            }
                        ) {
                            Text(text = if (running) "Running..." else "Start")
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        OutlinedButton(
                            enabled = !running,
                            onClick = {
                                running = false
                                progress = 0f
                            }
                        ) {
                            Text("Reset")
                        }
                    }
                }

                // 🔹 Lower half: Color state feature
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.TopCenter
                ) {

                    val shape = RoundedCornerShape(12.dp)

                    ElevatedCard(
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = colorList[colorIndex]
                        ),
                        modifier = Modifier
                            .size(300.dp, 250.dp)
                            .clip(shape)
                            .clickable(
                                indication = ripple(bounded = true),
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                colorIndex = (colorIndex + 1) % colorList.size
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
        }
    }
}
