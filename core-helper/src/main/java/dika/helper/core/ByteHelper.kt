package dika.helper.core

import dika.helper.core.constant.ByteUnit
import dika.helper.core.model.ByteSize

object ByteHelper {

    fun convert(value: Long, byteUnitSource: ByteUnit, byteUnitDestination: ByteUnit): ByteSize {
        val inByte = value * byteUnitSource.multiplicationToByte
        val destinationValue = inByte / byteUnitDestination.multiplicationToByte
        return ByteSize(destinationValue, byteUnitDestination)
    }

}