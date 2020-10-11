package digital.klik.helper.security.encryption.aes

import digital.klik.helper.security.encryption.aes.constant.AesKeySize
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.exception.SecretKeyException

abstract class BaseAes128MessageEncryption(mode: EncryptionMode): BaseAesMessageEncryption(EncryptionAlgorithm.AES_128, mode) {

    override fun onValidateSecretKey(secretKey: String) {
        val length = secretKey.length
        if (length != AesKeySize.SIZE_128.requiredKeyLength) {
            throw SecretKeyException("Secret key length must be 128 bytes, the given is $length bytes")
        }
    }

}