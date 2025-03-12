package dika.helper.android.security.encryption.des

import dika.helper.android.security.encryption.PaddingMessageEncryption
import dika.helper.android.security.encryption.constant.EncryptionAlgorithm
import dika.helper.android.security.encryption.constant.EncryptionMode
import dika.helper.android.security.encryption.constant.SecretKeySize
import dika.helper.android.security.exception.SecretKeyException

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