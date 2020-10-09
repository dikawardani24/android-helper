package digital.klik.helper.security.encryption.constant

import digital.klik.helper.security.exception.SecurityException

enum class EncryptionMode(val value: String) {
    CBC("CBC"),
    ECB("ECB");

    companion object {
        fun from(mode: String): EncryptionMode {
            var found: EncryptionMode? = null

            for (encryptionMode in values()) {
                if (encryptionMode.value == mode) {
                    found = encryptionMode
                    break
                }
            }

            return found ?: throw SecurityException("Unsupported encryption mode for $mode")
        }
    }
}