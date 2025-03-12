package dika.helper.android.security.encryption.aes

import dika.helper.android.security.encryption.PaddingMessageEncryption
import dika.helper.android.security.encryption.constant.EncryptionAlgorithm
import dika.helper.android.security.encryption.constant.EncryptionMode
import dika.helper.android.security.encryption.constant.SecretKeySize
import dika.helper.android.security.exception.SecretKeyException

class Aes128MessageEncryption(mode: EncryptionMode): PaddingMessageEncryption(EncryptionAlgorithm.AES, mode) {

    override fun onValidateSecretKey(secretKey: String) {
        val length = secretKey.length
        val keySize = SecretKeySize.from(length)

        if (keySize != SecretKeySize.SIZE_16) {
            throw SecretKeyException("Secret key length must be 128 bytes, the given is $length bytes")
        }
    }

}