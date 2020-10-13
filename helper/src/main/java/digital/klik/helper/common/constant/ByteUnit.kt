package digital.klik.helper.common.constant

import digital.klik.helper.common.extension.pow

enum class ByteUnit(val multiplicationToByte: Long) {
    BYTE(1),
    KILO_BYTE(1024),
    MB(1024.pow(2)),
    GIGA_BYTE(1024.pow(3))
}
