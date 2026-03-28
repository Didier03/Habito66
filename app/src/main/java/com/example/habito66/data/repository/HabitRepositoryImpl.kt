package com.example.habito66.data.repository

import com.example.habito66.domain.model.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class HabitRepositoryImpl {
    private val _habits = MutableStateFlow<List<Habit>>(emptyList())
    val habits : StateFlow<List<Habit>> = _habits.asStateFlow()

    fun addHabit(name: String) {
        val newHabit = Habit(
            id = UUID.randomUUID().toString(),
            name = name
        )
        _habits.update { currentList -> currentList + newHabit }
    }
    fun getHabitById(id: String): Habit? {
        return _habits.value.find { it.id == id }
    }
    fun updateHabit(id: String, newName: String) {
        _habits.update { currentList ->
            currentList.map { habit ->
                if (habit.id == id) habit.copy(name = newName) else habit
            }
        }
    }

}