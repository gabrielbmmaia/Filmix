package com.example.filmix.core

import android.view.View
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Extensão para transformar uma data String em dd/MM/yyyy
 * */
fun String.toBrazilianDate(): String {
    val outputFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    val date = inputFormat.parse(this)
    return date?.let {
        outputFormat.format(date)
    } ?: ""
}

/**
 * Extensão para deixar a view Invisivel
 * */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}
