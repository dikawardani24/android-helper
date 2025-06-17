package com.example.wvLog.bridge

import android.util.Log
import android.webkit.JavascriptInterface

class ConsoleBridge {
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