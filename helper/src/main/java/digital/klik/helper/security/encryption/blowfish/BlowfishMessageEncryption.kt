package digital.klik.helper.security.encryption.blowfish

import digital.klik.helper.security.encryption.PaddingMessageEncryption
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import digital.klik.helper.security.encryption.constant.EncryptionMode
import digital.klik.helper.security.exception.SecretKeyException

class BlowfishMessageEncryption(
    encryptionMode: EncryptionMode
) : PaddingMessageEncryption(EncryptionAlgorithm.BLOW_FISH, encryptionMode) {

    override fun onValidateSecretKey(secretKey: String) {
        if (secretKey.isEmpty()) {
            throw SecretKeyException("Key must not be empty")
        }
    }


}