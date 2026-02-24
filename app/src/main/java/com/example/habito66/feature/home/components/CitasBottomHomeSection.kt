package com.example.habito66.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habito66.R
import com.example.habito66.ui.theme.AppColors
import com.example.habito66.ui.theme.InterBoldItalicText
import com.example.habito66.ui.theme.InterBoldText
import com.example.habito66.ui.theme.InterMediumText

@Composable
fun CitasBottomHomeSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 8.dp)
            .height(115.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(AppColors.citaBackground),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_citas),
                contentDescription = "Completado",
                modifier = Modifier.size(22.dp),
                tint = AppColors.secondaryColor
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column() {
                Text(
                    text = "\"El secreto para salir adelante es comenzar\"",
                    style = InterBoldItalicText,
                    color = AppColors.titleText,
                    fontSize = 14.sp
                )
                Text(
                    text = "— Mark Twain.",
                    style = InterBoldText,
                    color = AppColors.secondaryColor,
                    fontSize = 12.sp
                )
            }
        }
    }
}

