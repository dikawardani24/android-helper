package dika.helper.android.security

interface HashingService<T> {
    fun hash(data: T): String
    fun isMatched(encryptedData: String, data: T): Boolean
}