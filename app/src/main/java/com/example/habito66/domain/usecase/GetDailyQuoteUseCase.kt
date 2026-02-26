package com.example.habito66.domain.usecase

import com.example.habito66.domain.model.Quote
import com.example.habito66.domain.repository.QuoteRepository

class GetDailyQuoteUseCase(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): Result<Quote> {
        return repository.getDailyQuote()
    }
}