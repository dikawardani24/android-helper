package digital.klik.helper.security.hashing.constant

import digital.klik.helper.security.exception.SecurityException

enum class HashingAlgorithm(val value: String) {
    MD2("MD2"),
    MD5("MD5"),
    SHA_1("SHA-1"),
    SHA_224("SHA-224"),
    SHA_256("SHA-256"),
    SHA_384("SHA-384"),
    SHA_512("SHA-512");

    companion object {

        fun from(algorithmString: String): HashingAlgorithm {
            var found: HashingAlgorithm? = null

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