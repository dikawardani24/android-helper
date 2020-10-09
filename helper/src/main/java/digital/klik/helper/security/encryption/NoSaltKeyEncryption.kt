package digital.klik.helper.security.encryption

import android.util.Base64
import digital.klik.helper.security.EncryptionService
import digital.klik.helper.security.exception.SecurityException

class NoSaltKeyEncryption :
    EncryptionService<String> {

    override fun secure(data: String): String {
        return try {
            val byteArrayData = data.trim().toByteArray(Charsets.UTF_8)
            val encodedData = Base64.encode(byteArrayData, Base64.DEFAULT)
            String(encodedData, Charsets.UTF_8)
        } catch (e: Exception) {
            throw SecurityException("Unable to encrypt with no salt key caused by ${e.message}", e)
        }
    }

    override fun isMatched(encryptedData: String, data: String): Boolean {
        return secure(data) == encryptedData
    }

    override fun decrypt(encryptedData: String): String {
        try {
            val byteArrayEncryptedData = encryptedData.toByteArray(Charsets.UTF_8)
            val decoded = Base64.decode(byteArrayEncryptedData, Base64.DEFAULT)
            return String(decoded, Charsets.UTF_8)
        } catch (e: Exception) {
            throw SecurityException("Unable to decrypt with no salt key caused by ${e.message}", e)
        }
    }

}