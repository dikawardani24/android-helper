package digital.klik.helper.security.encryption.constant

import digital.klik.helper.security.exception.SecurityException

enum class EncryptionPadding(val value: String) {
    NO_PADDING("NoPadding"),
    PKCS_5_PADDING("PKCS5Padding"),
    PKCS_1_PADDING("PKCS1Padding"),
    OAEP_PADDING("OAEPPadding"),
    OAEP_WITH_SHA_1_AND_MGF_1_PADDING("OAEPWithSHA-1AndMGF1Padding"),
    OAEP_WITH_SHA_256_AND_MGF_1_PADDING("OAEPWithSHA-256AndMGF1Padding"),
    OAEP_WITH_SHA_224_AND_MGF_1_PADDING("OAEPWithSHA-224AndMGF1Padding"),
    OAEP_WITH_SHA_384_AND_MGF_1_PADDING("OAEPWithSHA-384AndMGF1Padding"),
    OAEP_WITH_SHA_512_AND_MGF_1_PADDING("OAEPWithSHA-512AndMGF1Padding"),
    ISO_10126_PADDING("ISO10126Padding");

    companion object {
        fun from(padding: String): EncryptionPadding {
            var found: EncryptionPadding? = null

            for (encryptionPadding in values()) {
                if (encryptionPadding.value == padding) {
                    found = encryptionPadding
                    break
                }
            }

            return found ?: throw SecurityException("Unsupported padding for $padding")
        }
    }
}