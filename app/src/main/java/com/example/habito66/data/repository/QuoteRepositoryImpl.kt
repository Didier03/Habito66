package com.example.habito66.data.repository

import android.util.Log
import com.example.habito66.data.remote.api.QuoteRemoteDataSource
import com.example.habito66.domain.model.Quote
import com.example.habito66.domain.repository.QuoteRepository

class QuoteRepositoryImpl(
    private val remoteDataSource: QuoteRemoteDataSource
) : QuoteRepository {

    override suspend fun getDailyQuote(): Result<Quote> {
        return try {
            val response = remoteDataSource.fetchDailyQuote()

            Result.success(response.toDomain())
        } catch (e: Exception) {
            Log.e("QuoteRepository", "Error fetching daily quote", e)
            // Aquí atraparías excepciones de red (ej. sin internet)
            Result.failure(e)
        }
    }
}