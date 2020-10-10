package digital.klik.helper.security.encryption

import android.util.Base64
import digital.klik.helper.security.EncryptionService
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.encryption.constant.EncryptionPadding
import digital.klik.helper.security.exception.SecurityException
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

abstract class BaseMessageEncryption (
    private val algorithm: EncryptionAlgorithm,
    private val encryptionMode: EncryptionMode
) : EncryptionService<String> {
    lateinit var encryptionPadding: EncryptionPadding
    protected lateinit var secretKey: SecretKey

    protected abstract fun onValidateSecretKey(secretKey: String)

    private fun initCipher(): Cipher {

        if (!this::encryptionPadding.isInitialized) {
            throw SecurityException("Padding is not initialized")
        }

        if (!this::secretKey.isInitialized) {
            throw SecurityException("Secret key is not initialized")
        }

        return Cipher.getInstance("${algorithm.value}/${encryptionMode.value}/${encryptionPadding.value}")
    }

    protected abstract fun onInitCipherEncrypt(cipher: Cipher)
    protected abstract fun onInitCipherDecrypt(cipher: Cipher)

    fun setSecretKey(secretKey: String) {
        onValidateSecretKey(secretKey)
        val secretBytes = secretKey.toByteArray(Charsets.UTF_8)
        this.secretKey = SecretKeySpec(secretBytes, algorithm.value)
    }

    override fun encrypt(data: String): String {
        return try {
            val cipher = initCipher()
            onInitCipherEncrypt(cipher)
            val cipherText = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
            Base64.encodeToString(cipherText, Base64.DEFAULT)
        } catch (e: Exception) {
            throw SecurityException(e.message, e)
        }
    }

    override fun isMatched(encryptedData: String, data: String): Boolean {
        return try {
            val decrypted = decrypt(encryptedData)
            decrypted == data
        } catch (e: Exception) {
            throw SecurityException(e.message, e)
        }
    }

    override fun decrypt(encryptedData: String): String {
        return try {
            val cipher = initCipher()
            onInitCipherDecrypt(cipher)
            val bytes = Base64.decode(encryptedData, Base64.DEFAULT)
            val cipherText = cipher.doFinal(bytes)
            String(cipherText)
        } catch (e: Exception) {
            throw SecurityException(e.message, e)
        }
    }
}