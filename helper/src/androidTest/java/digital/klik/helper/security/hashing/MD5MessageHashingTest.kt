package digital.klik.helper.security.hashing

import digital.klik.helper.common.extension.logDebug
import org.junit.Test

import org.junit.Assert.*

class MD5MessageHashingTest {
    private val hashing = MD5MessageHashing()

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