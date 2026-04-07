package com.example.habito66.data.remote.api

import com.example.habito66.data.remote.dto.QuoteDto
import com.example.habito66.domain.model.Quote

interface QuoteRemoteDataSource {
    suspend fun fetchDailyQuote(): QuoteDto
}

