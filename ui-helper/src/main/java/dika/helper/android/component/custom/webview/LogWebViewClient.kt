package dika.helper.android.component.custom.webview

import android.content.Context
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import dika.helper.android.component.extension.sendNotification

abstract class LogWebViewClient(
    private val isDebug: Boolean,
    private val context: Context
): WebChromeClient() {

    abstract fun onConsoleLog(consoleMessage: ConsoleMessage)

    final override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        consoleMessage ?: return false
        if (!isDebug) return false
        onConsoleLog(consoleMessage)
        context.sendNotification(
            channelId = "LOG_WEB_VIEW",
            smallIconRes = android.R.drawable.ic_dialog_info,
            color = android.R.color.holo_blue_light,
            title = "Log Web View",
            message = consoleMessage.message()
        )
        return super.onConsoleMessage(consoleMessage)
    }

}