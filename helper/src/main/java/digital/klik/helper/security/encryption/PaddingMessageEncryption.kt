package digital.klik.helper.security.encryption

import android.util.Base64
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.encryption.constant.EncryptionPadding
import digital.klik.helper.security.exception.IvParameterException
import digital.klik.helper.security.exception.PaddingException
import digital.klik.helper.security.exception.SecretKeyException
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

abstract class PaddingMessageEncryption (
    private val algorithm: EncryptionAlgorithm,
    private val encryptionMode: EncryptionMode
) : BaseEncryption<String>() {
    lateinit var encryptionPadding: EncryptionPadding
    private lateinit var secretKey: SecretKey
    lateinit var iv: IvParameterSpec

    protected abstract fun onValidateSecretKey(secretKey: String)

    private fun initCipher(): Cipher {

        if (!this::encryptionPadding.isInitialized) {
            throw PaddingException("Padding is not initialized")
        }

        if (!this::secretKey.isInitialized) {
            throw SecretKeyException("Secret key is not initialized")
        }

        return Cipher.getInstance("${algorithm.value}/${encryptionMode.value}/${encryptionPadding.value}")
    }

    private fun onInitCipherEncrypt(cipher: Cipher) {
        when (encryptionMode) {
            EncryptionMode.ECB -> {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            }

            else -> {
                if (!this::iv.isInitialized) {
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
                    iv = IvParameterSpec(cipher.iv)
                } else {
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
                }
            }
        }
    }

    private fun onInitCipherDecrypt(cipher: Cipher) {
        when (encryptionMode) {
            EncryptionMode.ECB -> {
                cipher.init(Cipher.DECRYPT_MODE, secretKey)
            }

            else -> {
                if (!this::iv.isInitialized) {
                    throw IvParameterException("Iv Parameter is required for AES Decryption")
                }

                cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
            }
        }
    }

    fun setSecretKey(secretKey: String) {
        onValidateSecretKey(secretKey)
        val secretBytes = secretKey.toByteArray(Charsets.UTF_8)
        this.secretKey = SecretKeySpec(secretBytes, algorithm.value)
    }

    override fun encrypt(data: String): String {
        var toEncrypt = data

        if (encryptionPadding == EncryptionPadding.NO_PADDING) {
            while (toEncrypt.toByteArray(Charsets.UTF_8).size % 16 != 0) {
                toEncrypt += '\u0020'
            }
        }

        return try {
            val cipher = initCipher()
            onInitCipherEncrypt(cipher)
            val cipherText = cipher.doFinal(toEncrypt.toByteArray(Charsets.UTF_8))
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
            val cipher = initCipher()
            onInitCipherDecrypt(cipher)
            val bytes = Base64.decode(encryptedData, Base64.DEFAULT)
            val cipherText = cipher.doFinal(bytes)

            val data = String(cipherText)
            if (encryptionPadding == EncryptionPadding.NO_PADDING) {
                data.trim()
            } else {
                data
            }
        } catch (e: Exception) {
            throw handleError(e)
        }
    }
}