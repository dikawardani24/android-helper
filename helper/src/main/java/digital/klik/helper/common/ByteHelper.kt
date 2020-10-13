package digital.klik.helper.common

import digital.klik.helper.common.constant.ByteUnit
import digital.klik.helper.common.model.ByteSize

object ByteHelper {

    fun convert(value: Long, byteUnitSource: ByteUnit, byteUnitDestination: ByteUnit): ByteSize {
        val inByte = value * byteUnitSource.multiplicationToByte
        val destinationValue = inByte / byteUnitDestination.multiplicationToByte
        return ByteSize(destinationValue, byteUnitDestination)
    }

}