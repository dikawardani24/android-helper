package dika.helper.android.security.encryption

import androidx.test.platform.app.InstrumentationRegistry
import dika.helper.android.R
import dika.helper.android.common.extension.logDebug
import dika.helper.android.security.encryption.base64.Base64MessageEncryption
import org.junit.Assert.assertTrue
import org.junit.Test

class Base64MessageEncryptionTest {

    private val noSaltKeyEncryption = Base64MessageEncryption()
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun secure() {
        val toEncrypt = appContext.getString(R.string.testMessage)
        val encrypted = noSaltKeyEncryption.encrypt(toEncrypt)

        logDebug("secure => toEncrypted : $toEncrypt, encrypted: $encrypted")
        assertTrue(toEncrypt != encrypted)
    }

    @Test
    fun isMatched() {
        val toEncrypt = appContext.getString(R.string.testMessage)
        val encrypted = noSaltKeyEncryption.encrypt(toEncrypt)
        logDebug("isMatched => toEncrypted : $toEncrypt, encrypted: $encrypted")

        assertTrue(noSaltKeyEncryption.isMatched(encrypted, toEncrypt))
    }

    @Test
    fun decrypt() {
        val toEncrypt = appContext.getString(R.string.testMessage)
        val encrypted = noSaltKeyEncryption.encrypt(toEncrypt)
        val decrypted = noSaltKeyEncryption.decrypt(encrypted)

        logDebug("decrypt => toEncrypted : $toEncrypt, encrypted: $encrypted, decrypted: $decrypted")
        assertTrue(decrypted == toEncrypt)
    }
}