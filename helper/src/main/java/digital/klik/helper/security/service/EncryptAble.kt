package digital.klik.helper.security.service

interface EncryptAble<T> {
    fun secure(data: T): String
    fun isMatched(encryptedData: String, data: T): Boolean
}