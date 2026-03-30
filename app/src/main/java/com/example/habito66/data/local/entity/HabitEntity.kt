package com.example.habito66.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.habito66.domain.model.Habit

@Entity(tableName = "habits_table")
data class HabitEntity(
    @PrimaryKey val id: String,
    val name: String,
    val progress: String,
    val streak: String,
    val isCompleted: Boolean
) {
    fun toDomain() = Habit(id, name, progress, streak, isCompleted)
}
fun Habit.toEntity() = HabitEntity(id, name, progress, streak, isCompleted)