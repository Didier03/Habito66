package com.example.habito66.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habito66.data.repository.HabitRepositoryImpl
import com.example.habito66.domain.usecase.GetDailyQuoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface QuoteUiState {
    data object Loading : QuoteUiState
    data class Success(val quoteText: String, val author: String) : QuoteUiState
    data class Error(val message: String) : QuoteUiState
}

class HomeViewModel(
    private val getDailyQuoteUseCase: GetDailyQuoteUseCase,
    private val habitRepository: HabitRepositoryImpl
) : ViewModel() {

    private val _quoteState = MutableStateFlow<QuoteUiState>(QuoteUiState.Loading)
    val quoteState = _quoteState.asStateFlow()
    val habitsList = habitRepository.habits.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000), // Evita reconexiones si giras la pantalla rápido
        initialValue = emptyList() // El valor inicial lo dicta el ViewModel, no la UI
    )

    init {
        fetchDailyQuote()
    }

    private fun fetchDailyQuote() {
        viewModelScope.launch {
            _quoteState.update { QuoteUiState.Loading }

            getDailyQuoteUseCase().fold(
                onSuccess = { quote ->
                    _quoteState.update { QuoteUiState.Success(quote.text, quote.author) }
                },
                onFailure = { error ->
                    _quoteState.update { QuoteUiState.Error("Error al cargar la cita.") }
                }
            )
        }
    }
}