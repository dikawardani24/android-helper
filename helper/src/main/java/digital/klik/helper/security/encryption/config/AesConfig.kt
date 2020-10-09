package digital.klik.helper.security.encryption.config

import digital.klik.helper.security.encryption.constant.AesCipherTransformation
import digital.klik.helper.security.encryption.constant.AesKeySize

data class AesConfig(
    val keySize: AesKeySize,
    val transformation: AesCipherTransformation
)