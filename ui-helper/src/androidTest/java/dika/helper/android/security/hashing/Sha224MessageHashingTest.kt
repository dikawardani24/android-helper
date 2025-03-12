package dika.helper.android.security.hashing

import androidx.test.platform.app.InstrumentationRegistry
import dika.helper.android.R
import dika.helper.core.extension.logDebug
import dika.helper.android.security.hashing.constant.HashingAlgorithm
import org.junit.Assert.*
import org.junit.Test

class Sha224MessageHashingTest {

    private val hashing = MessageHashing(HashingAlgorithm.SHA_224)
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