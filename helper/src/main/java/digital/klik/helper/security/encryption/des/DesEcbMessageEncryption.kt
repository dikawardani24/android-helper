package digital.klik.helper.security.encryption.des

import digital.klik.helper.security.encryption.constant.EncryptionMode
import javax.crypto.Cipher

class DesEcbMessageEncryption : BaseDesMessageEncryption(EncryptionMode.ECB) {
    override fun onInitCipherEncrypt(cipher: Cipher) {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    }

    override fun onInitCipherDecrypt(cipher: Cipher) {
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
    }
}