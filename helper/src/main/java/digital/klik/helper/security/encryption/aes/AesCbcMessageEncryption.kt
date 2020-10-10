package digital.klik.helper.security.encryption.aes

import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.exception.IvParameterException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

class AesCbcMessageEncryption : BaseAesMessageEncryption(EncryptionMode.CBC) {
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
            throw IvParameterException("Iv Parameter is required for AES Decryption")
        }

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
    }

}