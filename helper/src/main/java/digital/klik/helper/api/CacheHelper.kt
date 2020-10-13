package digital.klik.helper.api

import digital.klik.helper.api.exception.CacheException
import digital.klik.helper.common.constant.ByteUnit
import digital.klik.helper.common.extension.toByte
import digital.klik.helper.common.model.ByteSize
import okhttp3.Cache
import java.io.File

@Suppress("MemberVisibilityCanBePrivate", "unused")
object CacheHelper {

    fun createOrLoadCacheFile(parent: File, filename: String, limitByteSize: ByteSize): Cache {
        if (!parent.isDirectory) {
            throw CacheException("The parent is not directory")
        }

        val cacheFile = File(parent, filename)
        if (!cacheFile.isFile) {
            cacheFile.createNewFile()
        }

        val cacheSize = limitByteSize.toByte().value
        return Cache(cacheFile, cacheSize)
    }

    fun createOrLoadCacheFile(parent: File, filename: String, limitSize: Long, unit: ByteUnit): Cache {
        val limitByteSize = ByteSize(limitSize, unit)
        return createOrLoadCacheFile(parent, filename, limitByteSize)
    }

}