package digital.klik.helper.security.encryption.constant

import digital.klik.helper.security.exception.SecurityException

enum class EncryptionMode(val value: String, val supportedAlgorithms: List<EncryptionAlgorithm>) {
    CBC(
        value = "CBC",
        supportedAlgorithms = listOf(
            EncryptionAlgorithm.AES,
            EncryptionAlgorithm.AES_128,
            EncryptionAlgorithm.AES_256,
            EncryptionAlgorithm.BLOW_FISH,
            EncryptionAlgorithm.DES,
            EncryptionAlgorithm.DES_EDE
        )
    ),
    ECB(
        value = "ECB",
        supportedAlgorithms =listOf(
            EncryptionAlgorithm.AES,
            EncryptionAlgorithm.AES_128,
            EncryptionAlgorithm.AES_256,
            EncryptionAlgorithm.ARC_4,
            EncryptionAlgorithm.BLOW_FISH,
            EncryptionAlgorithm.DES,
            EncryptionAlgorithm.DES_EDE,
            EncryptionAlgorithm.RSA
        )
    ),
    CFB(
        value = "CFB",
        supportedAlgorithms = listOf(
            EncryptionAlgorithm.AES,
            EncryptionAlgorithm.BLOW_FISH,
            EncryptionAlgorithm.DES,
            EncryptionAlgorithm.DES_EDE
        )
    ),
    CTR(
        value = "CTR",
        supportedAlgorithms = listOf(
            EncryptionAlgorithm.AES,
            EncryptionAlgorithm.BLOW_FISH,
            EncryptionAlgorithm.DES,
            EncryptionAlgorithm.DES_EDE
        )
    ),
    CTS(
        value = "CTS",
        supportedAlgorithms = listOf(
            EncryptionAlgorithm.AES,
            EncryptionAlgorithm.BLOW_FISH,
            EncryptionAlgorithm.DES,
            EncryptionAlgorithm.DES_EDE
        )
    ),
    OFB(
        value = "OFB",
        supportedAlgorithms = listOf(
            EncryptionAlgorithm.AES,
            EncryptionAlgorithm.BLOW_FISH,
            EncryptionAlgorithm.DES,
            EncryptionAlgorithm.DES_EDE
        )
    ),
    GCM(
        value = "GCM",
        supportedAlgorithms = listOf(
            EncryptionAlgorithm.AES,
            EncryptionAlgorithm.AES_128,
            EncryptionAlgorithm.AES_256
        )
    ),
    NONE(
        value = "NONE",
        supportedAlgorithms = listOf(
            EncryptionAlgorithm.ARC_4,
            EncryptionAlgorithm.CHA_CHA_20,
            EncryptionAlgorithm.RSA
        )
    ),
    POLY_1305(
        value  = "Poly1305",
        supportedAlgorithms = listOf(
            EncryptionAlgorithm.CHA_CHA_20
        )
    );

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