package digital.klik.helper.security.encryption

import digital.klik.helper.security.EncryptionService
import digital.klik.helper.security.exception.SecurityException

abstract class BaseEncryption<T> : EncryptionService<T> {

    protected fun handleError(error: Exception): Exception {
        return if (error is SecurityException) {
            error
        } else {
            SecurityException(error.message, error)
        }
    }

}