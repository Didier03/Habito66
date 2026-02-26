package com.example.habito66.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiConstants {
    const val TIMEOUT_MILLIS = 15_000L // 15 segundos
}
fun createKtorClient(baseUrl: String): HttpClient {
    return HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(DefaultRequest) {
            url(baseUrl) // <-- Ahora es dinámico
        }
        install(HttpTimeout) {
            requestTimeoutMillis = ApiConstants.TIMEOUT_MILLIS
            connectTimeoutMillis = ApiConstants.TIMEOUT_MILLIS
            socketTimeoutMillis = ApiConstants.TIMEOUT_MILLIS
        }
    }
}