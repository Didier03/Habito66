package com.example.habito66.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habito66.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits_table")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habits_table WHERE id = :id LIMIT 1")
    suspend fun getHabitById(id: String): HabitEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Si el ID ya existe, lo actualiza (Ideal para Editar)
    suspend fun insertHabit(habit: HabitEntity)

    @Query("DELETE FROM habits_table WHERE id = :id")
    suspend fun deleteHabitById(id: String)
}