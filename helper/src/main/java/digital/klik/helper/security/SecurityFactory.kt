package digital.klik.helper.security

import digital.klik.helper.security.constant.Algorithm
import digital.klik.helper.security.service.MessageEncryptionService

object SecurityFactory {

    fun getEncryptionService(algorithm: Algorithm): MessageEncryptionService {
        return when(algorithm) {
            Algorithm.MD5 -> MD5Encryption()
            Algorithm.AES -> AESMessageEncryption()
        }
    }

}