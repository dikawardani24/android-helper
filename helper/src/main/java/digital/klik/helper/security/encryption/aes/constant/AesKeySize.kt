package digital.klik.helper.security.encryption.aes.constant

import digital.klik.helper.security.exception.SecurityException

enum class AesKeySize(val size: Int, val round: Int, val requiredKeyLength: Int) {
    SIZE_128(128, 10, 16),
    SIZE_192(192, 12, 24),
    SIZE_256(256, 14, 32);

    override fun toString(): String {
        return "${javaClass.simpleName}, key size: $size, round : $round"
    }

    companion object {
        private fun from(comparatorEquaility: (aesKeySize: AesKeySize) -> Boolean): AesKeySize? {
            var found: AesKeySize? = null
            for (aesKeySize in values()) {
                val matched = comparatorEquaility(aesKeySize)
                if (matched) {
                    found = aesKeySize
                    break
                }
            }

            return found
        }

        fun fromKeySize(keySize: Int): AesKeySize {
            val found =
                from { keySize == it.size }
            return found ?: throw SecurityException("Unsupported aes key size for key size : $keySize")
        }

        fun fromRoundSize(round: Int): AesKeySize {
            val found =
                from { round == it.round }
            return found ?: throw SecurityException("Unsupported aes key size for round size : $round")
        }

        fun fromSupportedKeyLength(keyLenght: Int): AesKeySize {
            val found =
                from { keyLenght == it.requiredKeyLength }
            return found ?: throw SecurityException("Unsupported aes key size for round size : $keyLenght")
        }
    }
}