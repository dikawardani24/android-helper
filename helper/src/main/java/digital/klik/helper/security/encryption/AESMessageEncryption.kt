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
    lateinit var encryptionMode: EncryptionMode
    lateinit var encryptionPadding: EncryptionPadding
    private lateinit var secretKey: SecretKey

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
            throw SecurityException("Invalid secret key length, the given key length : $length, must on of 16, 24 or 32")
        }
    }

    fun setSecretKey(secretKey: String) {
        validateSecretKey(secretKey)
        val secretBytes = secretKey.toByteArray(Charsets.UTF_8)
        this.secretKey = SecretKeySpec(secretBytes, algorithm.value)
    }

    private fun initCipher(cipherMode: Int): Cipher {
        if (!this::encryptionMode.isInitialized) {
            throw SecurityException("Mode is not initialized")
        }

        if (!this::encryptionPadding.isInitialized) {
            throw SecurityException("Padding is not initialized")
        }

        if (!this::secretKey.isInitialized) {
            throw SecurityException("Secret key is not initialized")
        }

        val cipher = Cipher.getInstance("${algorithm.value}/${encryptionMode.value}/${encryptionPadding.value}")
        cipher.init(cipherMode, secretKey)
        return cipher
    }

    override fun secure(data: String): String {
        val cipher = initCipher(Cipher.ENCRYPT_MODE)
        val cipherText = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(cipherText, Base64.DEFAULT)
    }

    override fun isMatched(encryptedData: String, data: String): Boolean {
        val e = secure(data)
        LoggerHelper.debug(this, "$e == $encryptedData ? ${e == encryptedData}")
        return e == encryptedData
    }

    override fun decrypt(encryptedData: String): String {
        val cipher = initCipher(Cipher.DECRYPT_MODE)
        val bytes = Base64.decode(encryptedData, Base64.DEFAULT)
        val cipherText = cipher.doFinal(bytes)
        return String(cipherText)
    }
}