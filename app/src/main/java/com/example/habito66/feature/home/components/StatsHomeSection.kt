package com.example.habito66.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatsHomeSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Item 1: Objetivo
        Box(modifier = Modifier.weight(1f).height(80.dp).background(Color.LightGray).padding(4.dp)) {
            Text("Objetivo de hoy", modifier = Modifier.align(Alignment.Center))
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Item 2: Días actuales
        Box(modifier = Modifier.weight(1f).height(80.dp).background(Color.LightGray).padding(4.dp)) {
            Text("Días actuales", modifier = Modifier.align(Alignment.Center))
        }
    }
}