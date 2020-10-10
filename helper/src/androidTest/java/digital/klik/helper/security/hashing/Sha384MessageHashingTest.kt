package digital.klik.helper.security.hashing

import androidx.test.platform.app.InstrumentationRegistry
import digital.klik.helper.R
import digital.klik.helper.common.extension.logDebug
import digital.klik.helper.security.hashing.constant.HashingAlgorithm
import org.junit.Assert.*
import org.junit.Test

class Sha384MessageHashingTest {
    private val hashing = MessageHashing(HashingAlgorithm.SHA_384)
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun hash() {
        val data = appContext.getString(R.string.testMessage)
        val hashed = hashing.hash(data)
        logDebug("Data : $data, Hashed : $hashed")
        assertTrue(data != hashed)
    }

    @Test
    fun isMatched() {
        val data = appContext.getString(R.string.testMessage)
        val hashed = hashing.hash(data)
        val matched = hashing.isMatched(hashed, data)
        logDebug("Data : $data, Hashed : $hashed, Matched : $matched")

        assertTrue(matched)
    }
}