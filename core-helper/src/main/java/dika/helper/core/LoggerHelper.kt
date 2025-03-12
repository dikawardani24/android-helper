package dika.helper.core

import android.util.Log
import dika.helper.core.config.EnvironmentConfig

@Suppress("unused")
object LoggerHelper {
    private fun isDebugging(): Boolean {
        return EnvironmentConfig.isDebuggingMode()
    }

    private fun createTag(from: Any): String {
        var tag = from.javaClass.simpleName
        if (tag.length > 23) {
            tag = tag.substring(0, 22)
        }

        return tag
    }

    fun debug(from: Any, message: String, throwable: Throwable? = null) {
       if (isDebugging()) {
           val tag = createTag(from)

           if (throwable != null) {
               Log.d(tag, message, throwable)
           } else {
               Log.d(tag, message)
           }
       }
    }

    fun info(from: Any, message: String, throwable: Throwable? = null) {
        if (isDebugging()) {
            val tag = createTag(from)
            if (throwable != null) {
                Log.i(tag, message, throwable)
            } else {
                Log.i(tag, message)
            }
        }
    }

    fun warning(from: Any, message: String, throwable: Throwable? = null) {
        if (isDebugging()) {
            val tag = createTag(from)
            if (throwable != null) {
                Log.w(tag, message, throwable)
            } else {
                Log.w(tag, message)
            }
        }
    }

    fun error(from: Any, message: String, throwable: Throwable? = null) {
        if (isDebugging()) {
            val tag = createTag(from)
            if (throwable != null) {
                Log.e(tag, message, throwable)
            } else {
                Log.e(tag, message)
            }
        }
    }
}