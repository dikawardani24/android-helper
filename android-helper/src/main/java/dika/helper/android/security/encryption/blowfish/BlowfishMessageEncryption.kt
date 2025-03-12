package dika.helper.android.security.encryption.blowfish

import dika.helper.android.security.encryption.PaddingMessageEncryption
import dika.helper.android.security.encryption.constant.EncryptionAlgorithm
import dika.helper.android.security.encryption.constant.EncryptionMode
import dika.helper.android.security.exception.SecretKeyException

class BlowfishMessageEncryption(
    encryptionMode: EncryptionMode
) : PaddingMessageEncryption(EncryptionAlgorithm.BLOW_FISH, encryptionMode) {

    override fun onValidateSecretKey(secretKey: String) {
        if (secretKey.isEmpty()) {
            throw SecretKeyException("Key must not be empty")
        }
    }


}