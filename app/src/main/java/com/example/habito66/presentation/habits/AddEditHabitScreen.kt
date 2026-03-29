package com.example.habito66.presentation.habits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
            habitName = viewModel.getInitialHabitName(id)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
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