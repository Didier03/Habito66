package com.example.habito66.presentation.habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habito66.data.repository.HabitRepositoryImpl
import com.example.habito66.domain.model.Habit
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreateHabitViewModel(
    private val habitRepository: HabitRepositoryImpl
) : ViewModel() {

    private val _uiEvent = Channel<HomeUiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    private var recentlyDeletedHabit: Habit? = null
    fun getInitialHabitName(id: String, onResult: (String) -> Unit) {
        viewModelScope.launch {
            val name = habitRepository.getHabitById(id)?.name ?: ""
            onResult(name)
        }
    }
    fun saveOrUpdateHabit(id: String, name: String, onSuccess: () -> Unit) {
        if (name.isNotBlank()) {
            viewModelScope.launch {
                habitRepository.saveOrUpdateHabit(id, name)

                onSuccess()
            }
        }
    }
    fun deleteHabitById(id: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            habitRepository.deleteHabit(id)
            onSuccess()
        }
    }
    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            recentlyDeletedHabit = habit
            habitRepository.deleteHabit(habit.id)
            _uiEvent.send(HomeUiEvent.ShowUndoSnackbar(habit))
        }
    }
    fun undoDelete() {
        viewModelScope.launch {
            recentlyDeletedHabit?.let { habit ->
                habitRepository.insertHabit(habit)
                recentlyDeletedHabit = null
            }
        }
    }
}