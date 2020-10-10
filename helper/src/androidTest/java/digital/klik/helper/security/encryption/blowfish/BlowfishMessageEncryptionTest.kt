package digital.klik.helper.security.encryption.blowfish

import digital.klik.helper.common.extension.logDebug
import org.junit.Assert.assertTrue
import org.junit.Test

class BlowfishMessageEncryptionTest {
    private val encryption = BlowfishMessageEncryption()

    init {
        encryption.run {
            setSecretKey("12345678912345678901234567890123 hfhfhfhhgfhgfggggggggggggggggggggggggggggggggggggggggggggggggggggg")
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
        logDebug( "To Encrypt: $toEncrypt, Encrypted: $encrypted")
        assertTrue(encryption.isMatched(encrypted, toEncrypt))
    }

    @Test
    fun decrypt() {
        val toEncrypt = "Dika Wardani"
        val encrypted = encryption.encrypt(toEncrypt)
        val decrypted = encryption.decrypt(encrypted)
        logDebug( "To Encrypt: $toEncrypt, Encrypted: $encrypted, Decrypted: $decrypted")
        assertTrue(decrypted == toEncrypt)
    }
}