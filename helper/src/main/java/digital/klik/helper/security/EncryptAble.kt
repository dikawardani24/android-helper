package digital.klik.helper.security

interface EncryptAble<T> {
    fun secure(data: T): String
    fun isMatched(encryptedData: String, data: T): Boolean
}

interface DecryptAble<T>: EncryptAble<T> {
    fun decrypt(encriptedData: String): T
}