package com.example.filmix.core

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.toBrazilianDate(): String {
    val outputFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    val date = inputFormat.parse(this)
    date?.let {
        return outputFormat.format(date)
    }
    return ""
}