package digital.klik.helper.security.constant

import digital.klik.helper.security.exception.SecurityException

enum class AesKeySize(val size: Int, val round: Int) {
    SIZE_128(128, 10),
    SIZE_192(192, 12),
    SIZE_256(256, 14);

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
                }
            }

            return found
        }

        fun fromKeySize(keySize: Int): AesKeySize {
            val found = from { keySize == it.size }
            return found ?: throw SecurityException("Unsupported aes key size for key size : $keySize")
        }

        fun fromRoundSize(round: Int): AesKeySize {
            val found = from { round == it.round }
            return found ?: throw SecurityException("Unsupported aes key size for round size : $round")
        }
    }
}