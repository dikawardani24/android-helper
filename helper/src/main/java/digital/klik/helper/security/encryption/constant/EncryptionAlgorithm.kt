package digital.klik.helper.security.encryption.constant

import digital.klik.helper.security.exception.SecurityException

enum class EncryptionAlgorithm(val value: String) {
    AES("AES"),
    AES_128("AES_128"),
    AES_256("AES_256"),
    DES("DES"),
    BLOW_FISH("Blowfish"),
    ARC_4("ARC4"),
    CHA_CHA_20("ChaCha20"),
    DES_EDE("DESede"),
    RSA("RSA");

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