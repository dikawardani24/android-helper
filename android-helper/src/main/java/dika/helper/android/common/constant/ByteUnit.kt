package dika.helper.android.common.constant

import dika.helper.android.common.extension.pow

enum class ByteUnit(val multiplicationToByte: Long) {
    BYTE(1),
    KILO_BYTE(1024),
    MB(1024.pow(2)),
    GIGA_BYTE(1024.pow(3))
}
