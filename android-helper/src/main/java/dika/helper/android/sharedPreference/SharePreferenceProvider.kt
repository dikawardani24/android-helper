package dika.helper.android.sharedPreference

interface SharePreferenceProvider {

    fun remove(key: String)

    fun store(key: String, value: Any)

    fun getString(key: String, default: String?): String?

    fun getStringOrEmpty(key: String): String

    fun getStringOrThrow(key: String): String

    fun getInt(key: String, default: Int?): Int?

    fun getIntOrEmpty(key: String): Int

    fun getIntOrThrow(key: String): Int

    fun getFloat(key: String, default: Float?): Float?

    fun getFloatOrEmpty(key: String): Float

    fun getFloatOrThrow(key: String): Float

    fun getLong(key: String, default: Long?): Long?

    fun getLongOrEmpty(key: String): Long

    fun getLongOrThrow(key: String): Long

    fun getStringSet(key: String, default: Set<String>?): Set<String>?

    fun getStringSetOrEmpty(key: String): Set<String>

    fun getStringSetOrThrow(key: String): Set<String>
}
