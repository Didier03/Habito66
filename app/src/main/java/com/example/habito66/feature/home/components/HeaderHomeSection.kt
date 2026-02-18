package com.example.habito66.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.habito66.ui.theme.InterBoldText
import com.example.habito66.ui.theme.InterMediumText
import com.example.habito66.utils.formattedDate

@Composable
fun HeaderHomeSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "El viaje de hoy", style = InterBoldText)
            val dateText = remember { formattedDate() }
            Text(text = dateText, style = InterMediumText, color = colorScheme.primary)
        }
        // Placeholder del icono de perfil
        Box(modifier = Modifier.size(48.dp).background(Color.Gray)) {
            Text("IMG", modifier = Modifier.align(Alignment.Center))
        }
    }
}


