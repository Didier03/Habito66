package com.example.habito66.domain.repository

import com.example.habito66.domain.model.Quote

interface QuoteRepository {
    suspend fun getDailyQuote(): Result<Quote>
}
