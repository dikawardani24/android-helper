package digital.klik.helper.security.encryption.des

import androidx.test.platform.app.InstrumentationRegistry
import digital.klik.helper.R
import digital.klik.helper.common.extension.logDebug
import digital.klik.helper.security.encryption.constant.EncryptionPadding
import org.junit.Assert.*
import org.junit.Test

class DesEcbMessageEncryptionTest {
    private val encryption = DesEcbMessageEncryption()
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        encryption.run {
            setSecretKey("12345678")
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