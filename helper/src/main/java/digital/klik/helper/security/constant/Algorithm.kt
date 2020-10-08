package digital.klik.helper.security.constant

import digital.klik.helper.security.exception.SecurityException

enum class Algorithm(val value: String) {
    MD5("MD5"),
    AES("AES");

    companion object {

        fun from(algorithmString: String): Algorithm {
            var found: Algorithm? = null

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