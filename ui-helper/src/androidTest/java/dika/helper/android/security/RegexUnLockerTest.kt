package dika.helper.android.security

import dika.helper.android.security.encryption.constant.EncryptionAlgorithm
import dika.helper.android.security.encryption.constant.EncryptionMode
import dika.helper.core.extension.logDebug
import org.junit.Assert.assertTrue
import org.junit.Test

class RegexUnLockerTest {

    @Test
    fun decrypt() {
        val regexString = "[A-Zg-z]"
        val unLocker = RegexUnLocker(regexString)

        val algoEncrypt = "5LuLv64KzyK656HwtHJ35GxG54S4tACA4"
        val algoDecrypted = unLocker.decrypt(algoEncrypt)
        logDebug("Plain text Algorithm : $algoDecrypted")
        assertTrue(algoDecrypted == "DESede")


        val algorithm = EncryptionAlgorithm.from(algoDecrypted)
        logDebug("Found algorithm : $algorithm")
        assertTrue(algorithm == EncryptionAlgorithm.DES_EDE)

        val modeEncrypted = "2FtEyF43DKoJzD45SzS4TA"
        val modeDecrypted = unLocker.decrypt(modeEncrypted)
        logDebug("Plain text mode : $modeDecrypted")
        assertTrue(modeDecrypted == "ECB")

        val mode = EncryptionMode.from(modeDecrypted)
        logDebug("Found mode : $mode")
        assertTrue(mode == EncryptionMode.ECB)
    }
}