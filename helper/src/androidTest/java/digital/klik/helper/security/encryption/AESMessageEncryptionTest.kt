package digital.klik.helper.security.encryption

import digital.klik.helper.common.LoggerHelper
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.encryption.constant.EncryptionPadding
import org.junit.Test

import org.junit.Assert.*

class AESMessageEncryptionTest {

    private val encryption = AESMessageEncryption()

    init {
        encryption.setSecretKey("12345678901234567890123456789012")
        encryption.encryptionMode = EncryptionMode.CBC
        encryption.encryptionPadding = EncryptionPadding.PKCS_5_PADDING
    }

    @Test
    fun secure() {
        val toEncrypt = "Dika Wardani"
        val encrypted = encryption.secure(toEncrypt)
        LoggerHelper.debug(this, "To Encrypt: $toEncrypt, Encrypted: $encrypted")
        assertTrue(toEncrypt != encrypted)
    }

    @Test
    fun isMatched() {
        val toEncrypt = "Dika Wardani"
        val encrypted = encryption.secure(toEncrypt)
        LoggerHelper.debug(this, "To Encrypt: $toEncrypt, Encrypted: $encrypted")
        assertTrue(encryption.isMatched(encrypted, toEncrypt))
    }

    @Test
    fun decrypt() {
        val toEncrypt = "Dika Wardani"
        val encrypted = encryption.secure(toEncrypt)
        val decrypted = encryption.decrypt(encrypted)
        LoggerHelper.debug(this, "To Encrypt: $toEncrypt, Encrypted: $encrypted, Decrypted: $decrypted")
        assertTrue(decrypted == toEncrypt)
    }
}