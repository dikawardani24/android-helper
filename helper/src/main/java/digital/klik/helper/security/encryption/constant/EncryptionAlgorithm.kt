package digital.klik.helper.security.encryption.constant

import digital.klik.helper.security.exception.SecurityException

enum class EncryptionAlgorithm(val value: String) {
    AES("AES"),
    DES("DES"),
    BLOW_FISH("Blowfish");

    companion object {

        fun from(algorithmString: String): EncryptionAlgorithm {
            var found: EncryptionAlgorithm? = null

            for (algorithm in values()) {
                if (algorithm.value == algorithmString) {
                    found = algorithm
                    break
                }
            }

            return found ?: throw SecurityException("Unsupported algorithm for $algorithmString")
        }
    }
}