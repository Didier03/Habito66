package com.example.habito66.data.remote.api

import com.example.habito66.data.remote.dto.QuoteDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class KtorQuoteRemoteDataSource(
    private val httpClient: HttpClient
) : QuoteRemoteDataSource {

    override suspend fun fetchDailyQuote(): QuoteDto {
        val rawString: String = httpClient.get("phrase").body()

        // 2. Le quitamos las comillas del principio y final, y limpiamos las barras invertidas
        val cleanJson = rawString.trim('"').replace("\\\"", "\"")

        // 3. Forzamos la deserialización manual
        return Json { ignoreUnknownKeys = true }.decodeFromString(cleanJson)
    }
}