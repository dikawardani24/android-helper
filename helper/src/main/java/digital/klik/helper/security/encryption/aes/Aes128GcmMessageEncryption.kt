package digital.klik.helper.security.encryption.aes

import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.exception.IvParameterException
import digital.klik.helper.security.exception.SecretKeyException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

class Aes128GcmMessageEncryption : BaseAesMessageEncryption(EncryptionAlgorithm.AES_128, EncryptionMode.GCM) {
    lateinit var iv: IvParameterSpec

    override fun onValidateSecretKey(secretKey: String) {
        val length = secretKey.length
        if (encryptionMode == EncryptionMode.GCM) {
            if (length != 16) {
                throw SecretKeyException("Secret key length for GCM mode must be 16, provided length : $length")
            }
        }
    }

    override fun onInitCipherEncrypt(cipher: Cipher) {
        if (!this::iv.isInitialized) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            iv = IvParameterSpec(cipher.iv)
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
        }
    }

    override fun onInitCipherDecrypt(cipher: Cipher) {
        if (!this::iv.isInitialized) {
            throw IvParameterException("Iv Parameter is required for AES Decryption")
        }

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
    }

}