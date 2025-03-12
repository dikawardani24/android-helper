package dika.helper.android.sharedPreference.valueLoader

interface ValueLoader<T> {
    fun get(key: String, default: T?): T?
    fun getOrEmpty(key: String): T
    fun getOrThrow(key: String): T
}