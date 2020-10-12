package digital.klik.helper.security

import digital.klik.helper.common.extension.logDebug
import org.junit.Assert.*
import org.junit.Test

class PaddingHelperTest {

    @Test
    fun paddingWithString() {
        val message = "Dika Ward"
        val padlength = 4
        val padstring = "S"

        val result = PaddingHelper.paddingWithString(message, padlength, padstring)
        logDebug("Result : $result")
        assertTrue(result == "Dika WardSSS")
    }
}