package digital.klik.helper.sharedPreference.valueLoader

interface ValueLoader<T> {
    fun get(default: T?): T?
    fun getOrEmpty(): T
    fun getOrThrow(): T
}