package com.example.habito66.data.remote.api

import com.example.habito66.data.remote.dto.QuoteDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorQuoteRemoteDataSource(
    private val httpClient: HttpClient
) : QuoteRemoteDataSource {

    override suspend fun fetchDailyQuote(): List<QuoteDto> {
        // Como ya configuramos la BASE_URL en el cliente,
        // aquí solo pasamos el endpoint final. Código mucho más limpio.
        return httpClient.get("today").body()
    }
}