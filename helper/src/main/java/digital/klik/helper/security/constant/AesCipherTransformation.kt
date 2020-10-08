package digital.klik.helper.security.constant

enum class AesCipherTransformation(val value: String) {
    CBS_PKCS_5_PADDING("AES/CBC/PKCS5PADDING");

    companion object {
        fun from(transformationString: String): AesCipherTransformation {
            var found: AesCipherTransformation? = null
            for (aesChiperTransformation in values()) {
                if (transformationString == transformationString) {
                    found = aesChiperTransformation
                }
            }

            return found ?: throw SecurityException("Unsupported cipher transformation for $transformationString")
        }
    }
}