package com.example.habito66.presentation.habits

import com.example.habito66.domain.model.Habit

sealed class HomeUiEvent {
    data class ShowUndoSnackbar(val habit: Habit) : HomeUiEvent()
}