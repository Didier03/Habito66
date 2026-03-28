package com.example.habito66.domain.model

data class Habit(
    val id: String,
    val name: String,
    val progress: String = "0/1",
    val streak: String = "0 dias de racha",
    val isCompleted: Boolean = false
)
