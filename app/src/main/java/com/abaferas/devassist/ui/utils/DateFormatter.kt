package com.abaferas.devassist.ui.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateFormatter {
    companion object {
        @SuppressLint("ConstantLocale")
        private val DATE_FORMAT= SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        fun convert(value: Long): String {
            val date = Date(value)
            return DATE_FORMAT.format(date)
        }

        fun convert(value: String): Long {
            val date = DATE_FORMAT.parse(value)
            return date?.time ?: 0L
        }
    }
}