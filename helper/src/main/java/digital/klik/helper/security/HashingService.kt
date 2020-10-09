package digital.klik.helper.security

interface HashingService<T> {
    fun secure(data: T): String
    fun isMatched(encryptedData: String, data: T): Boolean
}