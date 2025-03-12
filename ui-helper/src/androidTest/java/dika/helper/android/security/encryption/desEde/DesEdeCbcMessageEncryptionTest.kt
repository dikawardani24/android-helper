package dika.helper.android.security.encryption.desEde

import androidx.test.platform.app.InstrumentationRegistry
import dika.helper.android.R
import dika.helper.core.extension.logDebug
import dika.helper.android.security.encryption.constant.EncryptionMode
import dika.helper.android.security.encryption.constant.EncryptionPadding
import dika.helper.android.security.encryption.des.DesEdeMessageEncryption
import org.junit.Assert.assertTrue
import org.junit.Test

class DesEdeCbcMessageEncryptionTest {
    private val encryption = DesEdeMessageEncryption(EncryptionMode.CBC)
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        encryption.run {
            val secretKey = appContext.getString(R.string.aes_128_key)
            setSecretKey(secretKey)
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
        logDebug( "To Encrypt: $toEncrypt, Encrypted: $encrypted")
        assertTrue(encryption.isMatched(encrypted, toEncrypt))
    }

    @Test
    fun decrypt() {
        val toEncrypt = appContext.getString(R.string.testMessage)
        val encrypted = encryption.encrypt(toEncrypt)
        val decrypted = encryption.decrypt(encrypted)
        logDebug( "To Encrypt: $toEncrypt, Encrypted: $encrypted, Decrypted: $decrypted")
        assertTrue(decrypted == toEncrypt)
    }
}