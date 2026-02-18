package com.example.habito66.feature.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CitasBottomHomeSection() {
    // Este Box simula tu barra inferior o contenido fijo
    Surface(
        shadowElevation = 8.dp, // Sombra para separar visualmente
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp), // Altura de tu sección fija
            contentAlignment = Alignment.Center
        ) {
            Text("Apartado Fijo Abajo")
        }
    }
}
