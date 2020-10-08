package digital.klik.helper.security

import android.util.Base64
import digital.klik.helper.security.config.AesConfig
import digital.klik.helper.security.constant.AesCipherTransformation
import digital.klik.helper.security.constant.AesKeySize
import digital.klik.helper.security.constant.Algorithm
import digital.klik.helper.security.service.MessageDecryptionService
import digital.klik.helper.security.service.MessageEncryptionService
import javax.crypto.Cipher
import javax.crypto.KeyGenerator

class AESMessageEncryption: MessageEncryptionService, MessageDecryptionService {
    var aesConfig: AesConfig = AesConfig(
        keySize = AesKeySize.SIZE_256,
        transformation = AesCipherTransformation.CBS_PKCS_5_PADDING
    )

    override fun secure(data: String): String {
        val keySize = aesConfig.keySize.size
        val transformation = aesConfig.transformation.value
        val algorithm = Algorithm.AES.value

        val keygen = KeyGenerator.getInstance(algorithm)
        keygen.init(keySize)

        val secretKey = keygen.generateKey()
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val cipherText = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(cipherText, Base64.DEFAULT)
    }

    override fun isMatched(encryptedData: String, data: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun decrypt(encriptedData: String): String {
        TODO("Not yet implemented")
    }
}