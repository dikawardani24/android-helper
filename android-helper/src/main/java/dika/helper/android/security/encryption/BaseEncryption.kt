package dika.helper.android.security.encryption

import dika.helper.android.security.exception.SecurityException

abstract class BaseEncryption {

    protected fun handleError(error: Exception): Exception {
        return if (error is SecurityException) {
            error
        } else {
            SecurityException(error.message, error)
        }
    }

}