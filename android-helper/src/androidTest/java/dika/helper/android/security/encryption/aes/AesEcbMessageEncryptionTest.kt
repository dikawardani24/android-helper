package dika.helper.android.security.encryption.aes

import androidx.test.platform.app.InstrumentationRegistry
import dika.helper.android.R
import dika.helper.android.common.extension.logDebug
import dika.helper.android.security.encryption.constant.EncryptionMode
import dika.helper.android.security.encryption.constant.EncryptionPadding
import org.junit.Assert.assertTrue
import org.junit.Test

class AesEcbMessageEncryptionTest {
    private val encryption = AesMessageEncryption(EncryptionMode.ECB)
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        encryption.run {
            setSecretKey("12345678901234567890123456789012")
            encryptionPadding = EncryptionPadding.PKCS_5_PADDING
        }
    }

    @Test
    fun encrypt() {
        val toEncrypt = appContext.getString(R.string.testMessage)
        val encrypted = encryption.encrypt(toEncrypt)
        logDebug("To Encrypt: $toEncrypt, Encrypted: $encrypted")
        assertTrue(toEncrypt != encrypted)
    }

    @Test
    fun isMatched() {
        val toEncrypt = appContext.getString(R.string.testMessage)
        val encrypted = encryption.encrypt(toEncrypt)
        logDebug("To Encrypt: $toEncrypt, Encrypted: $encrypted")
        assertTrue(encryption.isMatched(encrypted, toEncrypt))
    }

    @Test
    fun decrypt() {
        val toEncrypt = appContext.getString(R.string.testMessage)
        val encrypted = encryption.encrypt(toEncrypt)
        val decrypted = encryption.decrypt(encrypted)
        logDebug("To Encrypt: $toEncrypt, Encrypted: $encrypted, Decrypted: $decrypted")
        assertTrue(decrypted == toEncrypt)
    }
}