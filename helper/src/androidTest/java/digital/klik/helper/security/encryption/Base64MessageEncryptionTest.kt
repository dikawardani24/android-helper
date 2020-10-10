package digital.klik.helper.security.encryption

import digital.klik.helper.common.extension.logDebug
import digital.klik.helper.security.encryption.base64.Base64MessageEncryption
import org.junit.Assert.assertTrue
import org.junit.Test

class Base64MessageEncryptionTest {

    private val noSaltKeyEncryption =
        Base64MessageEncryption()

    @Test
    fun secure() {
        val toEncrypt = "Dika Wardani \n Dona Doni"
        val encrypted = noSaltKeyEncryption.encrypt(toEncrypt)

        logDebug("secure => toEncrypted : $toEncrypt, encrypted: $encrypted")
        assertTrue(toEncrypt != encrypted)
    }

    @Test
    fun isMatched() {
        val toEncrypt = "Dika Wardani \n Dona Doni"
        val encrypted = noSaltKeyEncryption.encrypt(toEncrypt)
        logDebug("isMatched => toEncrypted : $toEncrypt, encrypted: $encrypted")

        assertTrue(noSaltKeyEncryption.isMatched(encrypted, toEncrypt))
    }

    @Test
    fun decrypt() {
        val toEncrypt = "Dika Wardani \n Dona Doni"
        val encrypted = noSaltKeyEncryption.encrypt(toEncrypt)
        val decrypted = noSaltKeyEncryption.decrypt(encrypted)

        logDebug("decrypt => toEncrypted : $toEncrypt, encrypted: $encrypted, decrypted: $decrypted")
        assertTrue(decrypted == toEncrypt)
    }
}