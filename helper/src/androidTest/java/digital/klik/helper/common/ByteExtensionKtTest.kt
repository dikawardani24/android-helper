package digital.klik.helper.common

import digital.klik.helper.common.constant.ByteUnit
import digital.klik.helper.common.extension.logDebug
import digital.klik.helper.common.extension.toByte
import digital.klik.helper.common.extension.toKiloByte
import org.junit.Test

import org.junit.Assert.*

class ByteExtensionKtTest {

    @Test
    fun toByte() {
        val value = 1
        val byteSize = value.toByte(ByteUnit.BYTE)
        assertTrue(byteSize.value == 1L && byteSize.unit == ByteUnit.BYTE )
    }

    @Test
    fun toKiloByte() {
        val value = 1
        val kiloByteSize = value.toKiloByte(ByteUnit.MB)
        logDebug(kiloByteSize.toString())
        assertTrue(kiloByteSize.value == 1024L && kiloByteSize.unit == ByteUnit.KILO_BYTE )
    }
}