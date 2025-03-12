package dika.helper.data

import dika.helper.data.constant.ByteUnit
import dika.helper.data.model.ByteSize

object ByteHelper {

    fun convert(value: Long, byteUnitSource: ByteUnit, byteUnitDestination: ByteUnit): ByteSize {
        val inByte = value * byteUnitSource.multiplicationToByte
        val destinationValue = inByte / byteUnitDestination.multiplicationToByte
        return ByteSize(destinationValue, byteUnitDestination)
    }

}