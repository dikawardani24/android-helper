package digital.klik.helper.security

import digital.klik.helper.common.extension.logDebug
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import org.junit.Test

import org.junit.Assert.*

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