package digital.klik.helper.security

import digital.klik.helper.common.LoggerHelper
import digital.klik.helper.security.encryption.Base64Encryption
import org.junit.Test

import org.junit.Assert.*

class Base64EncryptionTest {

    private val noSaltKeyEncryption =
        Base64Encryption()

    @Test
    fun secure() {
        val toEncrypt = "Dika Wardani \n Dona Doni"
        val encrypted = noSaltKeyEncryption.secure(toEncrypt)

        LoggerHelper.debug(this, "secure => toEncrypted : $toEncrypt, encrypted: $encrypted")
        assertTrue(toEncrypt != encrypted)
    }

    @Test
    fun isMatched() {
        val toEncrypt = "Dika Wardani \n Dona Doni"
        val encrypted = noSaltKeyEncryption.secure(toEncrypt)
        LoggerHelper.debug(this, "isMatched => toEncrypted : $toEncrypt, encrypted: $encrypted")

        assertTrue(noSaltKeyEncryption.isMatched(encrypted, toEncrypt))
    }

    @Test
    fun decrypt() {
        val toEncrypt = "Dika Wardani \n Dona Doni"
        val encrypted = noSaltKeyEncryption.secure(toEncrypt)
        val decrypted = noSaltKeyEncryption.decrypt(encrypted)

        LoggerHelper.debug(this, "decrypt => toEncrypted : $toEncrypt, encrypted: $encrypted, decrypted: $decrypted")

        assertTrue(decrypted == toEncrypt)
    }
}