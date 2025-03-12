package dika.helper.android.security

interface EncryptionServiceWithKey<T> : EncryptionService<T> {
    fun setSecretKey(secretKey: String)
}