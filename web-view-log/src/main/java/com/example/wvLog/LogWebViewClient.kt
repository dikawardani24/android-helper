package com.example.wvLog

import android.content.Context
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.app.NotificationCompat
import com.example.wvLog.bridge.ConsoleBridge
import com.example.wvLog.db.WvLogDatabase
import com.example.wvLog.db.entities.WvLogEntity
import com.example.wvLog.model.WebViewLog
import dika.helper.android.component.extension.sendNotification
import dika.helper.api.extension.execute
import dika.helper.core.Result
import dika.helper.core.config.EnvironmentConfig
import dika.helper.core.config.constant.Environment
import dika.helper.core.extension.logDebug
import dika.helper.database.repository.CrudRepository


abstract class LogWeViewChromeClient(
    private val isDebug: Boolean,
    private val context: Context,
    private val iconRes: Int,
    private val colorRes: Int,
    private val title: String = "Log Web View",
    webView: WebView
): WebChromeClient() {

    init {
        webView.addJavascriptInterface(ConsoleBridge(), "Console-Bridge")
    }

    private fun sendNotification(log: WebViewLog) {
        context.sendNotification(
            channelId = "WEB-VIEW-LOG",
            smallIconRes = iconRes,
            color = colorRes,
            title = if (log.sourceId.isNotEmpty()) "$title - ${log.sourceId}" else "$title - Javascript",
            message = log.message.ifEmpty { "No message log" },
            style = NotificationCompat.BigTextStyle().bigText(log.message)
        )
    }

    private fun saveLog(webViewLog: WebViewLog) {
        context.logDebug("level: ${webViewLog.level}, message: ${webViewLog.message}, lineNumber: ${webViewLog.lineNumber}, sourceID: ${webViewLog.sourceId}")
        val db = WvLogDatabase.getInstance(context)
        val repo = CrudRepository.create(db.wvLogDao())
        val entity = WvLogEntity.from(webViewLog)
        repo.save(entity).execute {
            db.close()
            if (it is Result.Success) {
                context.logDebug("Log saved with id ${it.data}")
                return@execute
            }
            if (it is Result.Failure) {
                context.logDebug(it.error.message ?: "Error save log web view")
            }
        }
    }

    open fun wvOnConsoleLog(consoleMessage: ConsoleMessage?) {}

    final override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        consoleMessage ?: return false
        if (!isDebug) return false
        EnvironmentConfig.changeEnvironmentMode(Environment.DEBUG)
        wvOnConsoleLog(consoleMessage)
        val webViewLog = WebViewLog.from(consoleMessage)
        sendNotification(webViewLog)
        saveLog(webViewLog)
        return super.onConsoleMessage(consoleMessage)
    }

    @Deprecated("Deprecated in Java")
    final override fun onConsoleMessage(message: String?, lineNumber: Int, sourceID: String?) {
    }


}