package com.example.habito66.presentation.habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habito66.data.repository.HabitRepositoryImpl
import com.example.habito66.domain.model.Habit
import com.example.habito66.domain.repository.HabitRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateHabitViewModel(
    private val habitRepository: HabitRepository
) : ViewModel() {
    private val _formState = MutableStateFlow(HabitFormState())
    val formState: StateFlow<HabitFormState> = _formState.asStateFlow()

    private val _navigationEvent = Channel<Unit>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun loadHabit(id: String) {
        viewModelScope.launch {
            val habit = habitRepository.getHabitById(id) ?: return@launch
            _formState.update { it.copy(name = habit.name) }
        }
    }

    fun onNameChange(name: String) =
        _formState.update { it.copy(name = name) }

    fun onIconSelected(index: Int) =
        _formState.update { it.copy(selectedIconIndex = index) }

    fun onColorSelected(index: Int) =
        _formState.update { it.copy(selectedColorIndex = index) }

    fun onRepeatModeChange(mode: RepeatMode) =
        _formState.update { it.copy(repeatMode = mode) }

    fun onGoalIncrement() =
        _formState.update { it.copy(goal = (it.goal + 1).coerceAtMost(99)) }

    fun onGoalDecrement() =
        _formState.update { it.copy(goal = (it.goal - 1).coerceAtLeast(1)) }

    fun saveOrUpdateHabit(id: String) {
        if (_formState.value.name.isBlank()) return
        viewModelScope.launch {
            habitRepository.saveOrUpdateHabit(id, _formState.value.name)
            _navigationEvent.send(Unit)
        }
    }
    fun deleteHabitById(id: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            habitRepository.deleteHabit(id)
            onSuccess()
        }
    }
}