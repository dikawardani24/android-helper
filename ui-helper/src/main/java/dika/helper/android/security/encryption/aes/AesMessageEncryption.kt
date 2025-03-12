package dika.helper.android.security.encryption.aes

import dika.helper.android.security.encryption.PaddingMessageEncryption
import dika.helper.android.security.encryption.constant.EncryptionAlgorithm
import dika.helper.android.security.encryption.constant.EncryptionMode
import dika.helper.android.security.encryption.constant.SecretKeySize
import dika.helper.android.security.exception.SecretKeyException

open class AesMessageEncryption(
    encryptionMode: EncryptionMode
) : PaddingMessageEncryption(EncryptionAlgorithm.AES, encryptionMode) {

    override fun onValidateSecretKey(secretKey: String) {
        val length = secretKey.length
        val keySize = SecretKeySize.from(length)

        if (keySize == SecretKeySize.SIZE_8) {
            throw SecretKeyException("Invalid secret key length, the given key length : $length, must on of 16, 24 or 32")
        }
    }
}