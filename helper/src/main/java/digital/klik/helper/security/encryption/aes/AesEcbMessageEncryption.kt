package digital.klik.helper.security.encryption.aes

import digital.klik.helper.security.encryption.AESMessageEncryption
import digital.klik.helper.security.encryption.constant.EncryptionMode
import javax.crypto.Cipher

class AesEcbMessageEncryption : AESMessageEncryption(EncryptionMode.ECB) {
    override fun onInitCipherEncrypt(cipher: Cipher) {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    }

    override fun onInitCipherDecrypt(cipher: Cipher) {
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
    }
}