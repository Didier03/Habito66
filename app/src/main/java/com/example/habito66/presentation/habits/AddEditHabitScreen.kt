package com.example.habito66.presentation.habits

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habito66.ui.componts.HabitFormDefaults
import com.example.habito66.ui.componts.HabitFormDefaults.habitClickable
import com.example.habito66.ui.theme.AppColors
import com.example.habito66.ui.theme.Habito66Theme
import com.example.habito66.ui.theme.InterBoldText
import com.example.habito66.ui.theme.InterRegularText
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditHabitScreen (
    id: String,
    onNavigateBack : () -> Unit,
    viewModel: CreateHabitViewModel = koinViewModel()
){
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val isEditing = id != "new_habit"

    LaunchedEffect(id) {
        if (isEditing) viewModel.loadHabit(id)
    }

    // Evento de navegación one-shot — no callback
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { onNavigateBack() }
    }
    AddEditHabitContent(
        formState = formState,
        isEditing = isEditing,
        onNameChange = viewModel::onNameChange,
        onIconSelected = viewModel::onIconSelected,
        onColorSelected = viewModel::onColorSelected,
        onRepeatModeChange = viewModel::onRepeatModeChange,
        onGoalIncrement = viewModel::onGoalIncrement,
        onGoalDecrement = viewModel::onGoalDecrement,
        onCancel = onNavigateBack,
        onSave = { viewModel.saveOrUpdateHabit(id) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditHabitContent(
    formState: HabitFormState,
    isEditing: Boolean,
    onNameChange: (String) -> Unit,
    onIconSelected: (Int) -> Unit,
    onColorSelected: (Int) -> Unit,
    onRepeatModeChange: (RepeatMode) -> Unit,
    onGoalIncrement: () -> Unit,
    onGoalDecrement: () -> Unit,
    onCancel: () -> Unit,
    onSave: () -> Unit
) {
    val accentColor = HabitFormDefaults.colors[formState.selectedColorIndex]

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Button(
                    onClick = onSave,
                    enabled = formState.name.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentColor,
                        disabledContainerColor = accentColor.copy(alpha = 0.4f)
                    )
                ) {
                    Text(
                        text = if (isEditing) "Guardar Cambios" else "Save Habit",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            // ── Header ──────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onCancel) {
                    Text(
                        text = "Cancel",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Text(
                    text = if (isEditing) "Edit Habit" else "New Habit",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                // Espacio simétrico para centrar el título
                Spacer(modifier = Modifier.width(72.dp))
            }

            HabitFormDivider()

            // ── Habit Name ───────────────────────────────────────────
            FormSection {
                FormSectionLabel(text = "HABIT NAME")
                Spacer(modifier = Modifier.height(10.dp))
                BasicTextField(
                    value = formState.name,
                    onValueChange = onNameChange,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    decorationBox = { inner ->
                        if (formState.name.isEmpty()) {
                            Text(
                                text = "e.g. Morning Yoga",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                        inner()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            HabitFormDivider()

            // ── Choose Icon ──────────────────────────────────────────
            FormSection {
                FormSectionLabel(text = "CHOOSE ICON")
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    HabitFormDefaults.icons.forEachIndexed { index, icon ->
                        val selected = formState.selectedIconIndex == index
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (selected) accentColor
                                    else MaterialTheme.colorScheme.surfaceVariant
                                )
                                .habitClickable { onIconSelected(index) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = if (selected) Color.White
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }

            HabitFormDivider()

            // ── Pick a Color ─────────────────────────────────────────
            FormSection {
                FormSectionLabel(text = "PICK A COLOR")
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    HabitFormDefaults.colors.forEachIndexed { index, color ->
                        val selected = formState.selectedColorIndex == index
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(color)
                                .then(
                                    // Anillo exterior cuando está seleccionado
                                    if (selected) Modifier.border(
                                        width = 2.5.dp,
                                        color = color.copy(alpha = 0.4f),
                                        shape = CircleShape
                                    ) else Modifier
                                )
                                .habitClickable { onColorSelected(index) },
                            contentAlignment = Alignment.Center
                        ) {
                            if (selected) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }

            HabitFormDivider()

            // ── Repeat ───────────────────────────────────────────────
            FormSection {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FormSectionLabel(text = "REPEAT")
                    // "Every day" en naranja — detalle del mockup
                    Text(
                        text = if (formState.repeatMode == RepeatMode.DAILY)
                            "Every day" else "Every week",
                        style = MaterialTheme.typography.bodyMedium,
                        color = accentColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                // Toggle segmentado con borde naranja
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.5.dp,
                            color = accentColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    RepeatMode.entries.forEach { mode ->
                        val selected = formState.repeatMode == mode
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    if (selected) accentColor else Color.Transparent
                                )
                                .habitClickable() { onRepeatModeChange(mode) }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = mode.label,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                color = if (selected) Color.White else accentColor
                            )
                        }
                    }
                }
            }

            HabitFormDivider()

            // ── Goal ─────────────────────────────────────────────────
            FormSection {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FormSectionLabel(text = "GOAL")
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        GoalStepButton(
                            label = "−",
                            onClick = onGoalDecrement,
                            color = accentColor
                        )
                        Text(
                            text = formState.goal.toString(),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.widthIn(min = 28.dp),
                            textAlign = TextAlign.Center
                        )
                        GoalStepButton(
                            label = "+",
                            onClick = onGoalIncrement,
                            color = accentColor
                        )
                    }
                }
            }

            HabitFormDivider()
        }
    }
}
// ── Componentes privados ──────────────────────────────────────────────────────

/**
 * Wrapper de sección con padding uniforme.
 * Toda sección del formulario tiene el mismo espaciado interno.
 */
@Composable
private fun FormSection(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        content = content
    )
}

/**
 * Label de sección — uppercase, gris, letter-spacing.
 * Fuente única de verdad para todos los labels del formulario.
 */
@Composable
private fun FormSectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.outline,
        letterSpacing = 1.2.sp,
        fontWeight = FontWeight.SemiBold
    )
}

/**
 * Divisor punteado entre secciones — detalle visual clave del mockup.
 */
@Composable
private fun HabitFormDivider() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        val dashWidth = 8.dp.toPx()
        val gapWidth = 6.dp.toPx()
        val strokeColor = Color(0xFFE0E0E0)
        var x = 0f
        while (x < size.width) {
            drawLine(
                color = strokeColor,
                start = Offset(x, 0f),
                end = Offset((x + dashWidth).coerceAtMost(size.width), 0f),
                strokeWidth = size.height
            )
            x += dashWidth + gapWidth
        }
    }
}

/**
 * Botón circular para incrementar/decrementar el Goal.
 */
@Composable
private fun GoalStepButton(label: String, onClick: () -> Unit, color: Color) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .border(width = 1.5.dp, color = color, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = color,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )
    }
}
@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun AddEditHabitContentCreatePreview() {
    Habito66Theme {
        AddEditHabitContent(
            formState = HabitFormState(),
            isEditing = false,
            onNameChange = {},
            onIconSelected = {},
            onColorSelected = {},
            onRepeatModeChange = {},
            onGoalIncrement = {},
            onGoalDecrement = {},
            onCancel = {},
            onSave = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun AddEditHabitContentEditPreview() {
    Habito66Theme {
        AddEditHabitContent(
            formState = HabitFormState(
                name = "Leer 30 minutos",
                selectedIconIndex = 1,
                selectedColorIndex = 2,
                repeatMode = RepeatMode.WEEKLY,
                goal = 3
            ),
            isEditing = true,
            onNameChange = {},
            onIconSelected = {},
            onColorSelected = {},
            onRepeatModeChange = {},
            onGoalIncrement = {},
            onGoalDecrement = {},
            onCancel = {},
            onSave = {}
        )
    }
}