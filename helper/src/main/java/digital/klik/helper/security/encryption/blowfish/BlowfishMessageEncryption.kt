package digital.klik.helper.security.encryption.blowfish

import android.util.Base64
import digital.klik.helper.security.encryption.BaseEncryption
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.exception.SecretKeyException
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class BlowfishMessageEncryption : BaseEncryption<String>() {
    private val algorithm = EncryptionAlgorithm.BLOW_FISH
    private lateinit var secretKey: SecretKey

    private fun onValidateSecretKey(secretKey: String) {
        if (secretKey.isEmpty()) {
            throw SecretKeyException("Key must not be empty")
        }
    }

    private fun initCipher(mode: Int): Cipher {
        if (!this::secretKey.isInitialized) {
            throw SecretKeyException("Secret key is not initialized")
        }

        val cipher = Cipher.getInstance(algorithm.value)
        cipher.init(mode, secretKey)
        return cipher
    }


    fun setSecretKey(secretKey: String) {
        onValidateSecretKey(secretKey)
        val secretBytes = secretKey.toByteArray(Charsets.UTF_8)
        this.secretKey = SecretKeySpec(secretBytes, algorithm.value)
    }

    override fun encrypt(data: String): String {
        return try {
            val cipher = initCipher(Cipher.ENCRYPT_MODE)
            val cipherText = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
            Base64.encodeToString(cipherText, Base64.DEFAULT)
        } catch (e: Exception) {
            throw handleError(e)
        }
    }

    override fun isMatched(encryptedData: String, data: String): Boolean {
        return try {
            val decrypted = decrypt(encryptedData)
            decrypted == data
        } catch (e: Exception) {
            throw handleError(e)
        }
    }

    override fun decrypt(encryptedData: String): String {
        return try {
            val cipher = initCipher(Cipher.DECRYPT_MODE)
            val bytes = Base64.decode(encryptedData, Base64.DEFAULT)
            val cipherText = cipher.doFinal(bytes)
            String(cipherText)
        } catch (e: Exception) {
            throw handleError(e)
        }
    }

}