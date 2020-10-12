package digital.klik.helper.security.encryption.aes

import digital.klik.helper.security.encryption.PaddingMessageEncryption
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.encryption.constant.SecretKeySize
import digital.klik.helper.security.exception.SecretKeyException

class Aes128MessageEncryption(mode: EncryptionMode): PaddingMessageEncryption(EncryptionAlgorithm.AES, mode) {

    override fun onValidateSecretKey(secretKey: String) {
        val length = secretKey.length
        val keySize = SecretKeySize.from(length)

        if (keySize != SecretKeySize.SIZE_16) {
            throw SecretKeyException("Secret key length must be 128 bytes, the given is $length bytes")
        }
    }

}