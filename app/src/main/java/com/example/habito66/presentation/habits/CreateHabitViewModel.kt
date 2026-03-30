package com.example.habito66.presentation.habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habito66.data.repository.HabitRepositoryImpl
import kotlinx.coroutines.launch

class CreateHabitViewModel(
    private val habitRepository: HabitRepositoryImpl
) : ViewModel() {
    fun getInitialHabitName(id: String, onResult: (String) -> Unit) {
        viewModelScope.launch {
            // Buscamos en Room en un hilo secundario
            val name = habitRepository.getHabitById(id)?.name ?: ""
            // Devolvemos el resultado a la UI
            onResult(name)
        }
    }
    fun saveOrUpdateHabit(id: String, name: String, onSuccess: () -> Unit) {
        if (name.isNotBlank()) {
            viewModelScope.launch {
                // Guardamos en Room en un hilo secundario
                habitRepository.saveOrUpdateHabit(id, name)

                // Avisamos a la UI que terminamos para que haga el popBackStack()
                onSuccess()
            }
        }
    }
    fun deleteHabit(id: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            habitRepository.deleteHabit(id)
            onSuccess()
        }
    }
}