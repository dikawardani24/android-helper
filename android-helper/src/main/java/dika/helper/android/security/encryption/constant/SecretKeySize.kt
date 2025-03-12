package dika.helper.android.security.encryption.constant

import dika.helper.android.security.exception.SecretKeyException

enum class SecretKeySize(val requiredKeyLength: Int) {
    SIZE_8(8),
    SIZE_16( 16),
    SIZE_24(24),
    SIZE_32( 32);

    companion object {
        fun from(keyLength: Int): SecretKeySize {
            var found: SecretKeySize? = null
            for (aesKeySize in values()) {
                val matched = keyLength == aesKeySize.requiredKeyLength
                if (matched) {
                    found = aesKeySize
                    break
                }
            }

            return found ?: throw SecretKeyException("Unsupported aes key size for round size : $keyLength")
        }
    }
}