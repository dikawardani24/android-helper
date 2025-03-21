package dika.helper.core.extension

import dika.helper.core.ByteHelper
import dika.helper.core.constant.ByteUnit
import dika.helper.core.model.ByteSize

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

