package dika.helper.android.security

interface EncryptionService<T> {
    fun encrypt(data: T): String
    fun isMatched(encryptedData: String, data: T): Boolean
    fun decrypt(encryptedData: String): T
}