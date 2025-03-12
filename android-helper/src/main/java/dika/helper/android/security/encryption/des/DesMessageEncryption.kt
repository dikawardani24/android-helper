package dika.helper.android.security.encryption.des

import dika.helper.android.security.encryption.PaddingMessageEncryption
import dika.helper.android.security.encryption.constant.EncryptionAlgorithm
import dika.helper.android.security.encryption.constant.EncryptionMode
import dika.helper.android.security.exception.SecretKeyException

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