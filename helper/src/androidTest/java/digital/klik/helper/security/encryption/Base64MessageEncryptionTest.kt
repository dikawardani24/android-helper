package digital.klik.helper.security.encryption

import digital.klik.helper.common.LoggerHelper
import org.junit.Assert.assertTrue
import org.junit.Test

class Base64MessageEncryptionTest {

    private val noSaltKeyEncryption =
        Base64MessageEncryption()

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