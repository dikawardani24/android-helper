package digital.klik.helper.security.hashing

import digital.klik.helper.common.extension.logDebug
import org.junit.Assert.assertTrue
import org.junit.Test

class Sha256MessageHashingTest {
    private val hashing = Sha256MessageHashing()

    @Test
    fun hash() {
        val data = "DIKA WARDANI"
        val hashed = hashing.hash(data)
        logDebug("Data : $data, Hashed : $hashed")
        assertTrue(data != hashed)
    }

    @Test
    fun isMatched() {
        val data = "DIKA WARDANI"
        val hashed = hashing.hash(data)
        val matched = hashing.isMatched(hashed, data)
        logDebug("Data : $data, Hashed : $hashed, Matched : $matched")

        assertTrue(matched)
    }
}