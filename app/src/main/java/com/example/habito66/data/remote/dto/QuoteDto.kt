package com.example.habito66.data.remote.dto

import com.example.habito66.domain.model.Quote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteDto(
    @SerialName("q") val text: String,
    @SerialName("a") val author: String
) {
    // Mapper (Función de extensión) para convertir de DTO a Entidad de Dominio
    fun toDomain(): Quote = Quote(text = text, author = author)
}