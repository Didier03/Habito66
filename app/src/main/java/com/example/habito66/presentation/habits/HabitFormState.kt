package com.example.habito66.presentation.habits

data class HabitFormState(
    val name: String = "",
    val selectedIconIndex: Int = 0,
    val selectedColorIndex: Int = 0,
    val repeatMode: RepeatMode = RepeatMode.DAILY,
    val goal: Int = 1
)


enum class RepeatMode(val label: String) {
    DAILY("Diario"),
    WEEKLY("Semanal")
}