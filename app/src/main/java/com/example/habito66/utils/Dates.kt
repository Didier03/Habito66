package com.example.habito66.utils

import java.util.Locale

fun formattedDate(): String {
    val locale = Locale.forLanguageTag("es-ES")
    return try {
        // Intentamos usar java.time (API 26+). Si falla, cae al fallback.
        val localDateClass = Class.forName("java.time.LocalDate")
        val nowMethod = localDateClass.getMethod("now")
        val dateObj = nowMethod.invoke(null)
        val formatterClass = Class.forName("java.time.format.DateTimeFormatter")
        val ofPatternMethod = formatterClass.getMethod("ofPattern", String::class.java, Locale::class.java)
        val formatter = ofPatternMethod.invoke(null, "d 'de' MMMM, yyyy", locale)
        val formatMethod = dateObj.javaClass.getMethod("format", formatter.javaClass)
        val formatted = formatMethod.invoke(dateObj, formatter) as String
        // Capitalizar la primera letra para coincidir con el ejemplo
        formatted.replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
    } catch (_: Exception) {
        // Fallback a SimpleDateFormat por compatibilidad con APIs antiguas
        val sdf = java.text.SimpleDateFormat("d 'de' MMMM, yyyy", locale)
        val formatted = sdf.format(java.util.Date())
        formatted.replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
    }
}