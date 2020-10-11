package digital.klik.helper.security.encryption.aes

import digital.klik.helper.security.encryption.PaddingMessageEncryption
import digital.klik.helper.security.encryption.aes.constant.AesKeySize
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.exception.SecretKeyException

abstract class BaseAesMessageEncryption(
    algorithm: EncryptionAlgorithm,
    encryptionMode: EncryptionMode
) : PaddingMessageEncryption(algorithm, encryptionMode) {

    override fun onValidateSecretKey(secretKey: String) {
        val length = secretKey.length
        var valid = false

        for (aesKeySize in AesKeySize.values()) {
            if (aesKeySize.requiredKeyLength == length) {
                valid = true
                break
            }
        }

        if (!valid) {
            throw SecretKeyException("Invalid secret key length, the given key length : $length, must on of 16, 24 or 32")
        }
    }
}