package com.example.habito66.presentation.habits

import androidx.lifecycle.ViewModel
import com.example.habito66.data.repository.HabitRepositoryImpl

class CreateHabitViewModel(
    private val habitRepository: HabitRepositoryImpl
) : ViewModel() {
    fun getInitialHabitName(id: String): String {
        return habitRepository.getHabitById(id)?.name ?: ""
    }
    fun saveOrUpdateHabit(id: String, name: String, onSuccess: () -> Unit) {
        if (name.isNotBlank()) {
            if (id == "new_habit") {
                habitRepository.addHabit(name) // Creamos
            } else {
                habitRepository.updateHabit(id, name) // Editamos
            }
            onSuccess()
        }
    }
}