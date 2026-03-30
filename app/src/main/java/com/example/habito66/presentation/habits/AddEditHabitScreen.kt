package com.example.habito66.presentation.habits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditHabitScreen (
    id: String,
    onNavigateBack : () -> Unit,
    viewModel: CreateHabitViewModel = koinViewModel()
){
    var habitName by remember { mutableStateOf("") }
    val isEditing = id != "new_habit"
    val screenTitle = if (isEditing) "Edit Habit" else "Create New Habit"
    LaunchedEffect(id) {
        if (isEditing) {
            viewModel.getInitialHabitName(id) { name ->
                habitName = name
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Quitamos verticalArrangement para que empiece arriba
    ) {
        Text(text = screenTitle, style = MaterialTheme.typography.headlineMedium)

        // Botón de eliminar solo visible si ya existe el hábito
        if (isEditing) {
            IconButton(onClick = {
                viewModel.deleteHabit(id = id) {
                    onNavigateBack() // Regresamos al Home después de borrar
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Habit",
                    tint = MaterialTheme.colorScheme.error // Rojo nativo del tema
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = habitName,
                    onValueChange = { habitName = it },
                    label = { Text("Habit Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        viewModel.saveOrUpdateHabit(id = id, name = habitName) {
                            onNavigateBack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isEditing) "Update" else "Save")
                }
                Text(text = screenTitle)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "ID: $id", color = Color.Red)
            }
        }
    }

}