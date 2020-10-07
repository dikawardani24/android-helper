package digital.klik.helper.common.extension

import digital.klik.helper.common.LoggerHelper

fun Any.logDebug(message: String, throwable: Throwable? = null) {
    LoggerHelper.debug(this, message, throwable)
}

fun Any.logInfo(message: String, throwable: Throwable? = null) {
    LoggerHelper.info(this, message, throwable)
}

fun Any.logWarning(message: String, throwable: Throwable? = null) {
    LoggerHelper.warning(this, message, throwable)
}

fun Any.logError(message: String, throwable: Throwable? = null) {
    LoggerHelper.error(this, message, throwable)
}

