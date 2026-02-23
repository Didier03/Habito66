package com.example.habito66.feature.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habito66.R
import com.example.habito66.ui.theme.AppColors
import com.example.habito66.ui.theme.InterBoldText
import com.example.habito66.ui.theme.InterMediumText

@Composable
fun StatsHomeSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatCard(
            modifier = Modifier.width(167.dp).height(131.dp),
            iconResourceId = R.drawable.icon_check,
            title = "OBJETIVO DIARIO",
            subtitle = "4/6",
            titleStyle = InterMediumText.copy(fontSize = 10.sp),
            titleColor = AppColors.statsSecondaryText,
            subtitleStyle = InterBoldText.copy(fontSize = 24.sp),
            subtitleColor = AppColors.statsPimaryText
        )

        Spacer(modifier = Modifier.width(16.dp))

        StatCard(
            modifier = Modifier.width(167.dp).height(131.dp),
            iconResourceId = R.drawable.icon_racha,
            title = "RACHA ACTUAL",
            subtitle = "14",
            titleStyle = InterMediumText.copy(fontSize = 10.sp),
            titleColor = AppColors.statsSecondaryText,
            subtitleStyle = InterBoldText.copy(fontSize = 24.sp),
            subtitleColor = AppColors.statsPimaryText
        )
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    iconResourceId: Int,
    title: String,
    subtitle: String,
    titleStyle: TextStyle = InterBoldText.copy(fontSize = 12.sp),
    titleColor: Color = AppColors.titleText,
    subtitleStyle: TextStyle = InterMediumText.copy(fontSize = 10.sp),
    subtitleColor: Color = AppColors.secondaryColor
) {
    Box(
        modifier = modifier
            .background(AppColors.cardBackground, RoundedCornerShape(12.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconResourceId),
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = AppColors.secondaryColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                title,
                style = titleStyle,
                color = titleColor
            )
            Text(
                subtitle,
                style = subtitleStyle,
                color = subtitleColor
            )
        }
    }
}