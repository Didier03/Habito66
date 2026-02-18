package com.example.habito66.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = OrangePrimaryDark,
    onPrimary = OnPrimaryDark,
    secondary = secondaryDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark
)

private val LightColorScheme = lightColorScheme(
    primary = OrangePrimaryLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLigth,
    background = BackgroundLight,
    surface = SurfaceLight,
    onSurface = onSurfaceLigth,
    surfaceVariant = surfaceVariantLigth
)

@Composable
fun Habito66Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}