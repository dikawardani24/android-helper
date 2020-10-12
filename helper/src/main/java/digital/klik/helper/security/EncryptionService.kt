package digital.klik.helper.security

interface EncryptionService<T> {
    fun setSecretKey(secretKey: String)
    fun encrypt(data: T): String
    fun isMatched(encryptedData: String, data: T): Boolean
    fun decrypt(encryptedData: String): T
}