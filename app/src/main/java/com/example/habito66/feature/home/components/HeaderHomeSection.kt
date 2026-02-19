package com.example.habito66.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habito66.ui.theme.AppColors
import com.example.habito66.ui.theme.InterBoldText
import com.example.habito66.ui.theme.InterLightText
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
            Text(text = "El viaje de hoy", style = InterBoldText, color = AppColors.titleText, fontSize = 33.sp)
            val dateText = remember { formattedDate() }
            Text(text = dateText, style = InterLightText, color = AppColors.secondaryColor, fontSize = 16.sp)
        }
        // Placeholder del icono de perfil
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(2.dp, AppColors.secondaryColor, CircleShape)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil de usuario",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}


