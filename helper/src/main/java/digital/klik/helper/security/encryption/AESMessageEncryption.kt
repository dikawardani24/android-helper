package digital.klik.helper.security.encryption

import android.util.Base64
import digital.klik.helper.security.EncryptionService
import digital.klik.helper.security.encryption.constant.AesKeySize
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.encryption.constant.EncryptionPadding
import digital.klik.helper.security.exception.SecurityException
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESMessageEncryption : EncryptionService<String> {
    private val algorithm = EncryptionAlgorithm.AES
    lateinit var encryptionMode: EncryptionMode
    lateinit var encryptionPadding: EncryptionPadding
    private lateinit var secretKey: SecretKey
    private lateinit var iv: IvParameterSpec

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

    private fun initCipher(): Cipher {
        if (!this::encryptionMode.isInitialized) {
            throw SecurityException("Mode is not initialized")
        }

        if (!this::encryptionPadding.isInitialized) {
            throw SecurityException("Padding is not initialized")
        }

        if (!this::secretKey.isInitialized) {
            throw SecurityException("Secret key is not initialized")
        }

        return Cipher.getInstance("${algorithm.value}/${encryptionMode.value}/${encryptionPadding.value}")
    }

    override fun secure(data: String): String {
        val cipher = initCipher()
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        iv = IvParameterSpec(cipher.iv)
        val cipherText = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(cipherText, Base64.DEFAULT)
    }

    override fun isMatched(encryptedData: String, data: String): Boolean {
        val decrypted = decrypt(encryptedData)
        return decrypted == data
    }

    override fun decrypt(encryptedData: String): String {
        val cipher = initCipher()
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
        val bytes = Base64.decode(encryptedData, Base64.DEFAULT)
        val cipherText = cipher.doFinal(bytes)
        return String(cipherText)
    }
}