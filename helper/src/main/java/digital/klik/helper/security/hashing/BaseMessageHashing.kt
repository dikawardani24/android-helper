package digital.klik.helper.security.hashing

import digital.klik.helper.security.HashingService
import digital.klik.helper.security.exception.SecurityException
import digital.klik.helper.security.hashing.constant.HashingAlgorithm
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

open class BaseMessageHashing(private val algorithm: HashingAlgorithm): HashingService<String> {
    override fun hash(data: String): String {
        return try {
            val messageDigest = MessageDigest.getInstance(algorithm.value)
            val offset = 0
            messageDigest.update(data.toByteArray(Charsets.UTF_8), offset, data.length)

            val signNUm = 1
            val radix = 16
            BigInteger(signNUm, messageDigest.digest()).toString(radix)

        } catch (e: NoSuchAlgorithmException) {
            throw SecurityException(e.message)
        }
    }

    override fun isMatched(encryptedData: String, data: String) = hash(data) == encryptedData

}