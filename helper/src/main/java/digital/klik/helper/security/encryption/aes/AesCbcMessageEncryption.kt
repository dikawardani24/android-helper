package digital.klik.helper.security.encryption.aes

import digital.klik.helper.security.encryption.AESMessageEncryption
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.exception.SecurityException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

class AesCbcMessageEncryption : AESMessageEncryption(EncryptionMode.CBC) {
    lateinit var iv: IvParameterSpec

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
            throw SecurityException("Iv Parameter is required for AES Decryption")
        }

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
    }

}