package com.example.habito66.data.repository

import com.example.habito66.data.remote.api.QuoteRemoteDataSource
import com.example.habito66.domain.model.Quote
import com.example.habito66.domain.repository.QuoteRepository

class QuoteRepositoryImpl(
    private val remoteDataSource: QuoteRemoteDataSource
) : QuoteRepository {

    override suspend fun getDailyQuote(): Result<Quote> {
        return try {
            val response = remoteDataSource.fetchDailyQuote()

            if (response.isNotEmpty()) {
                Result.success(response.first().toDomain())
            } else {
                Result.failure(Exception("La API devolvió una lista vacía"))
            }
        } catch (e: Exception) {
            // Aquí atraparías excepciones de red (ej. sin internet)
            Result.failure(e)
        }
    }
}