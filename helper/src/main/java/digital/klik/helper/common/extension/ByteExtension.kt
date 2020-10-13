package digital.klik.helper.common.extension

import digital.klik.helper.common.ByteHelper
import digital.klik.helper.common.constant.ByteUnit
import digital.klik.helper.common.model.ByteSize

fun Number.toByte(byteUnitSource: ByteUnit): ByteSize {
    return ByteHelper.convert(toLong(), byteUnitSource, ByteUnit.BYTE)
}

fun Number.toKiloByte(byteUnitSource: ByteUnit): ByteSize {
    return ByteHelper.convert(toLong(), byteUnitSource, ByteUnit.KILO_BYTE)
}

fun Number.toMegaByte(byteUnitSource: ByteUnit): ByteSize {
    return ByteHelper.convert(toLong(), byteUnitSource, ByteUnit.MB)
}

fun Number.toGigaByte(byteUnitSource: ByteUnit): ByteSize {
    return ByteHelper.convert(toLong(), byteUnitSource, ByteUnit.GIGA_BYTE)
}

fun ByteSize.toByte(): ByteSize {
    return ByteHelper.convert(
        value = value,
        byteUnitSource = unit,
        byteUnitDestination = ByteUnit.BYTE
    )
}

fun ByteSize.toKiloByte(): ByteSize {
    return ByteHelper.convert(
        value = value,
        byteUnitSource = unit,
        byteUnitDestination = ByteUnit.KILO_BYTE
    )
}

fun ByteSize.toGigaByte(): ByteSize {
    return ByteHelper.convert(
        value = value,
        byteUnitSource = unit,
        byteUnitDestination = ByteUnit.GIGA_BYTE
    )
}
fun ByteSize.toByte(byteSizeSource: ByteSize): ByteSize {
    return ByteHelper.convert(
        value = byteSizeSource.value,
        byteUnitSource = byteSizeSource.unit,
        byteUnitDestination = ByteUnit.BYTE
    )
}

