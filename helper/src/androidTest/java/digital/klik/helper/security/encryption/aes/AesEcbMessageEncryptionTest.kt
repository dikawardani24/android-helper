package digital.klik.helper.security.encryption.aes

import digital.klik.helper.common.extension.logDebug
import digital.klik.helper.security.encryption.constant.EncryptionPadding
import org.junit.Assert.assertTrue
import org.junit.Test

class AesEcbMessageEncryptionTest {
    private val encryption = AesEcbMessageEncryption()

    init {
        encryption.run {
            setSecretKey("12345678901234567890123456789012")
            encryptionPadding = EncryptionPadding.PKCS_5_PADDING
        }
    }

    @Test
    fun encrypt() {
        val toEncrypt = "Dika Wardani"
        val encrypted = encryption.encrypt(toEncrypt)
        logDebug("To Encrypt: $toEncrypt, Encrypted: $encrypted")
        assertTrue(toEncrypt != encrypted)
    }

    @Test
    fun isMatched() {
        val toEncrypt = "Dika Wardani"
        val encrypted = encryption.encrypt(toEncrypt)
        logDebug("To Encrypt: $toEncrypt, Encrypted: $encrypted")
        assertTrue(encryption.isMatched(encrypted, toEncrypt))
    }

    @Test
    fun decrypt() {
        val toEncrypt = "Dika Wardani"
        val encrypted = encryption.encrypt(toEncrypt)
        val decrypted = encryption.decrypt(encrypted)
        logDebug("To Encrypt: $toEncrypt, Encrypted: $encrypted, Decrypted: $decrypted")
        assertTrue(decrypted == toEncrypt)
    }
}