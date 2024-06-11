package com.example.theming.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Blue10,
    primaryContainer = Blue20,
    onPrimaryContainer = White,
    secondary = Blue80,
    onSecondary = Blue10,
    secondaryContainer = Blue90,
    onSecondaryContainer = White,
    error = Blue95,
    background = Blue95,
    onBackground = Blue20,
    surface = Blue90,
    onSurface = Blue10
)

private val DarkColorScheme = darkColorScheme(
    primary = BlueGray10,
    onPrimary = BlueGray30,
    primaryContainer = Blue95 ,
    onPrimaryContainer = BlueGray20,
    secondary = White,
    onSecondary = Blue10 ,
    secondaryContainer = BlueGray10,
    onSecondaryContainer = Blue95 ,
    error = BlueGray20,
    onBackground = BlueGray20,
    onSurface = White,
    background = Black,
    surface = Black,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable ()-> Unit){
    val dynamicColor = Build.VERSION.SDK_INT>= Build.VERSION_CODES.S
    val colorScheme = when {
        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}