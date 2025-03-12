package dika.helper.android.common

import dika.helper.android.common.constant.ByteUnit
import dika.helper.android.common.extension.logDebug
import dika.helper.android.common.extension.toByte
import dika.helper.android.common.extension.toKiloByte
import org.junit.Assert.assertTrue
import org.junit.Test

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