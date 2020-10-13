package digital.klik.helper.security.encryption.aes

import androidx.test.platform.app.InstrumentationRegistry
import digital.klik.helper.R
import digital.klik.helper.common.extension.logDebug
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.encryption.constant.EncryptionPadding
import digital.klik.helper.security.exception.SecurityException
import org.junit.Assert.assertTrue
import org.junit.Test

class AesCfbMessageEncryptionTest {
    private val encryption = AesMessageEncryption(EncryptionMode.CFB)
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        encryption.run {
            setSecretKey("12345678901234567890123456789012")
            encryptionPadding = EncryptionPadding.PKCS_5_PADDING
        }
    }

    private fun executeEncryption(function: () -> Boolean) {
        try {
            assertTrue(function())
        } catch (e: Exception) {
            assertTrue(e is SecurityException)
        }
    }

    @Test
    fun encrypt() {
        executeEncryption {
            val toEncrypt = appContext.getString(R.string.testMessage)
            val encrypted = encryption.encrypt(toEncrypt)
            logDebug("To Encrypt: $toEncrypt, Encrypted: $encrypted")
            toEncrypt != encrypted
        }
    }

    @Test
    fun isMatched() {
       executeEncryption {
           val toEncrypt = appContext.getString(R.string.testMessage)
           val encrypted = encryption.encrypt(toEncrypt)
           logDebug( "To Encrypt: $toEncrypt, Encrypted: $encrypted")
           encryption.isMatched(encrypted, toEncrypt)
       }
    }

    @Test
    fun decrypt() {
       executeEncryption {
           val toEncrypt = appContext.getString(R.string.testMessage)
           val encrypted = encryption.encrypt(toEncrypt)
           val decrypted = encryption.decrypt(encrypted)
           logDebug( "To Encrypt: $toEncrypt, Encrypted: $encrypted, Decrypted: $decrypted")
           decrypted == toEncrypt
       }
    }
}