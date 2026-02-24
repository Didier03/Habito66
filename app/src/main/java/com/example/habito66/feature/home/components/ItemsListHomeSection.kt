package com.example.habito66.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.habito66.R
import com.example.habito66.ui.theme.AppColors

enum class HabitItemState {
    NORMAL, COMPLETED
}

@Composable
fun HabitItem(
    habitName: String,
    habitProgress: String,
    habitStreak: String,
    modifier: Modifier = Modifier,
    state: HabitItemState = HabitItemState.NORMAL
) {
    val backgroundColor = if (state == HabitItemState.COMPLETED) {
        AppColors.habitCompletedColor
    } else {
        AppColors.cardBackground
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Hábito",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = habitName,
                    modifier = if (state == HabitItemState.COMPLETED) {
                        Modifier.drawBehind {
                            val y = size.height / 2
                            drawLine(
                                color = Color(0xFF585757),
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = 2.dp.toPx()
                            )
                        }
                    } else {
                        Modifier
                    }
                )
                Row {
                    Text(text = habitProgress)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = habitStreak)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_racha),
                    contentDescription = "Completado",
                    modifier = Modifier.size(48.dp),
                    tint = AppColors.secondaryColor
                )
            }
        }
    }
}

@Composable
fun ItemsListHomeSection(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(8) { index ->
            val state = if (index % 2 == 0) HabitItemState.NORMAL else HabitItemState.COMPLETED
            HabitItem(
                habitName = "Beber Agua",
                habitProgress = "500/2000ml",
                habitStreak = "5 dias de racha",
                state = state
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemsListHomeSectionPreview() {
    ItemsListHomeSection()
}

@Preview(showBackground = true)
@Composable
fun HabitItemNormalPreview() {
    HabitItem(
        habitName = "Beber Agua",
        habitProgress = "500/2000ml",
        habitStreak = "5 dias de racha",
        state = HabitItemState.NORMAL
    )
}

@Preview(showBackground = true)
@Composable
fun HabitItemCompletedPreview() {
    HabitItem(
        habitName = "Beber Agua",
        habitProgress = "500/2000ml",
        habitStreak = "5 dias de racha",
        state = HabitItemState.COMPLETED
    )
}

