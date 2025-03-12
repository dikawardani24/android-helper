package dika.helper.android.common

import dika.helper.android.common.constant.ByteUnit
import dika.helper.android.common.model.ByteSize

object ByteHelper {

    fun convert(value: Long, byteUnitSource: ByteUnit, byteUnitDestination: ByteUnit): ByteSize {
        val inByte = value * byteUnitSource.multiplicationToByte
        val destinationValue = inByte / byteUnitDestination.multiplicationToByte
        return ByteSize(destinationValue, byteUnitDestination)
    }

}