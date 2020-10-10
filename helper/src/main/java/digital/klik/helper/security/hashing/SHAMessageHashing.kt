package digital.klik.helper.security.hashing

import digital.klik.helper.security.hashing.constant.HashingAlgorithm

class Sha1MessageHashing: BaseMessageHashing(HashingAlgorithm.SHA_1)

class Sha224MessageHashing: BaseMessageHashing(HashingAlgorithm.SHA_224)

class Sha256MessageHashing: BaseMessageHashing(HashingAlgorithm.SHA_256)

class Sha384MessageHashing: BaseMessageHashing(HashingAlgorithm.SHA_384)

class Sha512MessageHashing: BaseMessageHashing(HashingAlgorithm.SHA_512)