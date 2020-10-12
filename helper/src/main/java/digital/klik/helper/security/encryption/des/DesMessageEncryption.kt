package digital.klik.helper.security.encryption.des

import digital.klik.helper.security.encryption.PaddingMessageEncryption
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.exception.SecretKeyException

class DesMessageEncryption(
    encryptionMode: EncryptionMode
) : PaddingMessageEncryption(EncryptionAlgorithm.DES, encryptionMode) {

    override fun onValidateSecretKey(secretKey: String) {
        val length = secretKey.length
        val valid = length == 8

        if (!valid) {
            throw SecretKeyException("Invalid secret key length, the given key length : $length, must be 8")
        }
    }
}