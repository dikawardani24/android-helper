package digital.klik.helper.security.encryption.des

import digital.klik.helper.security.encryption.PaddingMessageEncryption
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.exception.SecretKeyException
import digital.klik.helper.security.exception.SecurityException

abstract class BaseDesMessageEncryption(
    encryptionMode: EncryptionMode
) : PaddingMessageEncryption(EncryptionAlgorithm.DES, encryptionMode) {

    override fun onValidateSecretKey(secretKey: String) {
        val length = secretKey.length
        val valid = length == 8

        if (!valid) {
            throw SecretKeyException("Invalid secret key length, the given key length : $length, must on of 16, 24 or 32")
        }
    }
}