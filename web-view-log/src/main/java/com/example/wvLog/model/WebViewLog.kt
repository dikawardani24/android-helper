package com.example.wvLog.model

import android.webkit.ConsoleMessage
import android.webkit.ConsoleMessage.MessageLevel
import java.util.Date

enum class WebViewLogLevel {
    TIP,
    LOG,
    WARNING,
    ERROR,
    DEBUG,
    UNKNOWN;

    companion object {
        fun from(level: MessageLevel?) = when(level) {
            MessageLevel.TIP -> TIP
            MessageLevel.LOG -> LOG
            MessageLevel.WARNING -> WARNING
            MessageLevel.ERROR -> ERROR
            MessageLevel.DEBUG -> DEBUG
            null -> UNKNOWN
        }
    }
};

data class WebViewLog(
    val id: Int = -1,
    val message: String,
    val lineNumber: Int,
    val sourceId: String,
    val level: WebViewLogLevel,
    val date: Date = Date()
) {
    companion object {
        fun from(consoleMessage: ConsoleMessage) = WebViewLog(
            message = consoleMessage.message().orEmpty(),
            lineNumber = consoleMessage.lineNumber(),
            sourceId = consoleMessage.sourceId().orEmpty(),
            level = WebViewLogLevel.from(consoleMessage.messageLevel())
        )
    }
}