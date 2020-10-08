package digital.klik.helper.security.service

interface DecryptAble<T> {
    fun decrypt(encriptedData: String): T
}