package com.example.habito66.domain.repository

import com.example.habito66.domain.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    val habits: Flow<List<Habit>>
    suspend fun saveOrUpdateHabit(id: String, name: String)
    suspend fun getHabitById(id: String): Habit?
    suspend fun deleteHabit(id: String)
    suspend fun insertHabit(habit: Habit)  // ← asegúrate que esté declarada aquí
}