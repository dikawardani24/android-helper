package digital.klik.helper.security.encryption.des

import digital.klik.helper.security.encryption.PaddingMessageEncryption
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.encryption.constant.SecretKeySize
import digital.klik.helper.security.exception.SecretKeyException

class DesEdeMessageEncryption(
    encryptionMode: EncryptionMode
) : PaddingMessageEncryption(EncryptionAlgorithm.DES_EDE, encryptionMode) {

    override fun onValidateSecretKey(secretKey: String) {
        val length = secretKey.length
        val keySize = SecretKeySize.from(length)
        val valid = keySize == SecretKeySize.SIZE_16 || keySize == SecretKeySize.SIZE_24

        if (!valid) {
            throw SecretKeyException("Invalid secret key length, the given key length : $length, must be 128 or 192")
        }
    }
}