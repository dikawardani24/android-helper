package digital.klik.helper.security

import digital.klik.helper.security.constant.Algorithm
import digital.klik.helper.security.exception.SecurityException
import digital.klik.helper.security.service.MessageEncryptionService
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MD5Encryption: MessageEncryptionService {

    override fun secure(data: String): String {
        return try {
            val algorithm = Algorithm.MD5.value
            val messageDigest = MessageDigest.getInstance(algorithm)
            val offset = 0
            messageDigest.update(data.toByteArray(Charsets.UTF_8), offset, data.length)

            val signNUm = 1
            val radix = 16
            BigInteger(signNUm, messageDigest.digest()).toString(radix)

        } catch (e: NoSuchAlgorithmException) {
            throw SecurityException(e.message)
        }
    }

    override fun isMatched(encryptedData: String, data: String) = secure(data) == encryptedData

}