package digital.klik.helper.security

interface EncryptionServiceWithKey<T> : EncryptionService<T> {
    fun setSecretKey(secretKey: String)
}