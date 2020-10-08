package digital.klik.helper.security

import digital.klik.helper.security.exception.SecurityException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MD5Encryption: EncryptAble<String> {
    override fun secure(data: String): String {
        return try {
            val messageDigest = MessageDigest.getInstance("MD5")
            val offset = 0
            messageDigest.update(data.toByteArray(), offset, data.length)
            BigInteger(1, messageDigest.digest()).toString(16)

        } catch (e: NoSuchAlgorithmException) {
            throw SecurityException(e.message)
        }
    }

    override fun isMatched(encryptedData: String, data: String) = secure(data) == encryptedData
}