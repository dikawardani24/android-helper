package digital.klik.helper.security.encryption

import androidx.test.ext.junit.runners.AndroidJUnit4
import digital.klik.helper.common.LoggerHelper
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Base64MessageEncryptionTest {

    private val noSaltKeyEncryption =
        Base64MessageEncryption()

    @Test
    fun secure() {
        val toEncrypt = "Dika Wardani \n Dona Doni"
        val encrypted = noSaltKeyEncryption.encrypt(toEncrypt)

        LoggerHelper.debug(this, "secure => toEncrypted : $toEncrypt, encrypted: $encrypted")
        assertTrue(toEncrypt != encrypted)
    }

    @Test
    fun isMatched() {
        val toEncrypt = "Dika Wardani \n Dona Doni"
        val encrypted = noSaltKeyEncryption.encrypt(toEncrypt)
        LoggerHelper.debug(this, "isMatched => toEncrypted : $toEncrypt, encrypted: $encrypted")

        assertTrue(noSaltKeyEncryption.isMatched(encrypted, toEncrypt))
    }

    @Test
    fun decrypt() {
        val toEncrypt = "Dika Wardani \n Dona Doni"
        val encrypted = noSaltKeyEncryption.encrypt(toEncrypt)
        val decrypted = noSaltKeyEncryption.decrypt(encrypted)

        LoggerHelper.debug(this, "decrypt => toEncrypted : $toEncrypt, encrypted: $encrypted, decrypted: $decrypted")

        assertTrue(decrypted == toEncrypt)
    }
}