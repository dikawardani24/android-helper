package digital.klik.helper.security.config

import digital.klik.helper.security.constant.AesCipherTransformation
import digital.klik.helper.security.constant.AesKeySize

data class AesConfig(
    val keySize: AesKeySize,
    val transformation: AesCipherTransformation
)