package digital.klik.helper.security.encryption.base64

import android.util.Base64
import digital.klik.helper.security.encryption.BaseEncryption

class Base64MessageEncryption : BaseEncryption<String>() {

    override fun encrypt(data: String): String {
        return try {
            val byteArrayData = data.trim().toByteArray(Charsets.UTF_8)
            val encodedData = Base64.encode(byteArrayData, Base64.DEFAULT)
            String(encodedData, Charsets.UTF_8)
        } catch (e: Exception) {
            throw handleError(e)
        }
    }

    override fun isMatched(encryptedData: String, data: String): Boolean {
        return try {
            encrypt(data) == encryptedData
        } catch (e: Exception) {
            throw handleError(e)
        }
    }

    override fun decrypt(encryptedData: String): String {
        return try {
            val byteArrayEncryptedData = encryptedData.toByteArray(Charsets.UTF_8)
            val decoded = Base64.decode(byteArrayEncryptedData, Base64.DEFAULT)
            String(decoded, Charsets.UTF_8)
        } catch (e: Exception) {
            throw handleError(e)
        }
    }

}