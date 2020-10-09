package digital.klik.helper.security.encryption

import android.util.Base64
import digital.klik.helper.common.LoggerHelper
import digital.klik.helper.security.EncryptionService
import digital.klik.helper.security.encryption.constant.*
import digital.klik.helper.security.exception.SecurityException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class AESMessageEncryption : EncryptionService<String> {
    private val algorithm = EncryptionAlgorithm.AES
    private lateinit var secretKey: SecretKey
    private var cipher: Cipher? = null

    lateinit var mode: EncryptionMode
    lateinit var padding: EncryptionPadding

    private fun initCipher(): Cipher {
        if (!this::mode.isInitialized) {
            throw SecurityException("Mode is not initialized")
        }

        if (!this::padding.isInitialized) {
            throw SecurityException("Padding is not initialized")
        }

        return Cipher.getInstance("${algorithm.value}/${mode.value}/${padding.value}")
    }

    private fun validateSecretKey(secretKey: String) {
        val length = secretKey.length
        var valid = false

        for (aesKeySize in AesKeySize.values()) {
            if (aesKeySize.requiredKeyLength == length) {
                valid = true
                break
            }
        }

        if (!valid) {
            throw SecurityException("Invalid secret key length, must on of 16, 24 or 32")
        }
    }

    fun setSecretKey(secretKey: String) {
        validateSecretKey(secretKey)
        val secretBytes = secretKey.toByteArray(Charsets.UTF_8)
        this.secretKey = SecretKeySpec(secretBytes, algorithm.value)
    }

    override fun secure(data: String): String {
        val currentCipher = initCipher()
        currentCipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val cipherText = currentCipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(cipherText, Base64.DEFAULT)
    }

    override fun isMatched(encryptedData: String, data: String): Boolean {
        val e = secure(data)
        LoggerHelper.debug(this, "$e == $encryptedData ? ${e == encryptedData}")
        return e == encryptedData
    }

    override fun decrypt(encryptedData: String): String {
        val currentCipher = initCipher()
        currentCipher.init(Cipher.DECRYPT_MODE, secretKey)

        val bytes = Base64.decode(encryptedData, Base64.DEFAULT)
        val cipherText = currentCipher.doFinal(bytes)
        return String(cipherText)
    }
}