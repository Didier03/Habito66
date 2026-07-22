package com.example.habito66.ui.componts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ripple


object HabitFormDefaults {

    val icons = listOf(
        Icons.Outlined.Favorite,
        Icons.Outlined.MenuBook,
        Icons.Outlined.WaterDrop,
        Icons.Outlined.DarkMode,
        Icons.Outlined.Coffee          // necesita material-icons-extended
    )

    val colors = listOf(
        Color(0xFFE8541A),  // naranja  ← accent principal
        Color(0xFF2F80ED),  // azul
        Color(0xFF27AE60),  // verde
        Color(0xFF8E44AD),  // morado
        Color(0xFFF1C40F),  // amarillo
        Color(0xFFE91E63)   // rosa
    )

    val accentOrange = Color(0xFFE8541A)

    @Composable
    fun Modifier.habitClickable(onClick: () -> Unit): Modifier = this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple(),
        onClick = onClick
    )
}