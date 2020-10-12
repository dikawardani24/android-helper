package digital.klik.helper.security.encryption.aes

import digital.klik.helper.security.encryption.PaddingMessageEncryption
import digital.klik.helper.security.encryption.constant.SecretKeySize
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.exception.IvParameterException
import digital.klik.helper.security.exception.SecretKeyException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

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