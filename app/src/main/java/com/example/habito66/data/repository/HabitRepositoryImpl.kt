package com.example.habito66.data.repository

import com.example.habito66.data.local.dao.HabitDao
import com.example.habito66.data.local.entity.HabitEntity
import com.example.habito66.domain.model.Habit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.util.UUID

class HabitRepositoryImpl(
    private val habitDao: HabitDao
) {
    val habits: Flow<List<Habit>> = habitDao.getAllHabits().map { entities ->
        entities.map { it.toDomain() }
    }
    suspend fun saveOrUpdateHabit(id: String, name: String) {
        val finalId = if (id == "new_habit") UUID.randomUUID().toString() else id

        val currentHabit = if (id != "new_habit") habitDao.getHabitById(id) else null

        val entity = HabitEntity(
            id = finalId,
            name = name,
            progress = currentHabit?.progress ?: "0/1",
            streak = currentHabit?.streak ?: "0 dias de racha",
            isCompleted = currentHabit?.isCompleted ?: false
        )

        habitDao.insertHabit(entity)
    }

    suspend fun getHabitById(id: String): Habit? {
        return habitDao.getHabitById(id)?.toDomain()
    }

    suspend fun deleteHabit(id: String) {
        habitDao.deleteHabitById(id)
    }

}