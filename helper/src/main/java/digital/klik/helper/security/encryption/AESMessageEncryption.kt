package digital.klik.helper.security.encryption

import android.util.Base64
import digital.klik.helper.security.EncryptionService
import digital.klik.helper.security.encryption.constant.AesCipherTransformation
import digital.klik.helper.security.encryption.constant.AesKeySize
import digital.klik.helper.security.encryption.constant.EncryptionAlgorithm
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class AESMessageEncryption:
    EncryptionService<String> {
    private val algorithm = EncryptionAlgorithm.AES
    private var secretKey: SecretKey
    private var cipher = Cipher.getInstance(AesCipherTransformation.CBS_PKCS_5_PADDING.value)

    init {
        val keygen = KeyGenerator.getInstance(algorithm.value)
        keygen.init(AesKeySize.SIZE_256.size)
        secretKey = keygen.generateKey()
    }

    override fun secure(data: String): String {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val cipherText = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(cipherText, Base64.DEFAULT)
    }

    override fun isMatched(encryptedData: String, data: String): Boolean {
        return decrypt(encryptedData) == data
    }

    override fun decrypt(encryptedData: String): String {
        TODO("Not yet implemented")
    }
}