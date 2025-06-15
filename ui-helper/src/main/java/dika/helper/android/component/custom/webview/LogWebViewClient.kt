package dika.helper.android.component.custom.webview

import android.content.Context
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.app.NotificationCompat
import dika.helper.android.component.extension.sendNotification
import dika.helper.core.config.EnvironmentConfig
import dika.helper.core.config.constant.Environment
import dika.helper.core.extension.logDebug


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

    private fun sendNotification(message: String?, sourceID: String?) {
        context.sendNotification(
            channelId = "WEB-VIEW-LOG",
            smallIconRes = iconRes,
            color = colorRes,
            title = if (!sourceID.isNullOrBlank()) "$title - $sourceID" else "$title - Javascript",
            message = message ?: "No message log",
            style = NotificationCompat.BigTextStyle().bigText(message)
        )
    }

    private fun saveLog(message: String?, lineNumber: String?, sourceID: String?) {
        context.logDebug("message: $message, lineNumber: $lineNumber, sourceID: $sourceID")
    }

    open fun onConsoleLog(message: String?) {}

    final override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        consoleMessage ?: return false
        if (!isDebug) return false
        EnvironmentConfig.changeEnvironmentMode(Environment.DEBUG)
        val message = consoleMessage.message()
        val sourceID = consoleMessage.sourceId()
        onConsoleLog(message)
        sendNotification(message, sourceID)
        saveLog(
            message = message,
            lineNumber = consoleMessage.lineNumber().toString(),
            sourceID = consoleMessage.sourceId()
        )
        return super.onConsoleMessage(consoleMessage)
    }

    @Deprecated("Deprecated in Java")
    final override fun onConsoleMessage(message: String?, lineNumber: Int, sourceID: String?) {
    }

    private class ConsoleBridge {
        @JavascriptInterface
        fun log(message: String?) {
            Log.d("WEBVIEW_CONSOLE", message!!) // Appears in Logcat
        }

        @JavascriptInterface
        fun error(message: String?) {
            Log.e("WEBVIEW_CONSOLE", message!!) // Appears in Logcat
        }

        @JavascriptInterface
        fun warn(message: String?) {
            Log.w("WEBVIEW_CONSOLE", message!!) // Appears in Logcat
        }
    }
}