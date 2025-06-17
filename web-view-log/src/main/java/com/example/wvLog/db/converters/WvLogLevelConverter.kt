package com.example.wvLog.db.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.wvLog.model.WebViewLogLevel

@ProvidedTypeConverter
class WvLogLevelConverter {

    @TypeConverter
    fun stringToWvLogLevel(value: String): WebViewLogLevel = when(value) {
        "TIP" -> WebViewLogLevel.TIP
        "LOG" -> WebViewLogLevel.LOG
        "WARNING" -> WebViewLogLevel.WARNING
        "ERROR" -> WebViewLogLevel.ERROR
        "DEBUG" -> WebViewLogLevel.DEBUG
        else -> WebViewLogLevel.UNKNOWN
    }

    @TypeConverter
    fun wvLogLevelToString(value: WebViewLogLevel): String = when(value) {
        WebViewLogLevel.TIP -> "TIP"
        WebViewLogLevel.LOG -> "LOG"
        WebViewLogLevel.WARNING -> "WARNING"
        WebViewLogLevel.ERROR -> "ERROR"
        WebViewLogLevel.DEBUG -> "DEBUG"
        WebViewLogLevel.UNKNOWN -> "UNKNOWN"
    }
}