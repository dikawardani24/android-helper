package digital.klik.helper.security.constant

import digital.klik.helper.security.exception.SecurityException

enum class HashingAlgorithm(val value: String) {
    MD5("MD5");

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