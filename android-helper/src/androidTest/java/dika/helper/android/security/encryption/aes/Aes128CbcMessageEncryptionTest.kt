package dika.helper.android.security.encryption.aes

import androidx.test.platform.app.InstrumentationRegistry
import dika.helper.android.R
import dika.helper.android.common.extension.logDebug
import dika.helper.android.security.encryption.constant.EncryptionMode
import dika.helper.android.security.encryption.constant.EncryptionPadding
import dika.helper.android.security.exception.SecurityException
import org.junit.Assert.*
import org.junit.Test

class Aes128CbcMessageEncryptionTest {
    private val encryption = Aes128MessageEncryption(EncryptionMode.CBC)
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        encryption.run {
            val secret = appContext.getString(R.string.aes_128_key)
            setSecretKey(secret)
            encryptionPadding = EncryptionPadding.NO_PADDING
        }
    }

    private fun executeEncryption(function: () -> Boolean) {
        try {
            assertTrue(function())
        } catch (e: Exception) {
            assertTrue(e is SecurityException)
            e.printStackTrace()
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