package digital.klik.helper.security.encryption.constant

import digital.klik.helper.security.exception.SecurityException

enum class EncryptionMode(val value: String) {
    CBC(value = "CBC"),
    ECB(value = "ECB"),
    CFB(value = "CFB"),
    CTR(value = "CTR"),
    CTS(value = "CTS"),
    OFB(value = "OFB"),
    GCM(value = "GCM"),
    NONE(value = "NONE"),
    POLY_1305(value  = "Poly1305");

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