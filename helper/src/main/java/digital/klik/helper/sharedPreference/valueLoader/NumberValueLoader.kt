package digital.klik.helper.sharedPreference.valueLoader

import android.content.SharedPreferences

abstract class NumberValueLoader<T: Number>(sharedPreferences: SharedPreferences, key: String): BaseValueLoader<T>(sharedPreferences, key) {
    protected val invalidValue = -1

    @Suppress("UNCHECKED_CAST")
    override fun getOrEmpty(): T {
        val foundValue = get(null)
        return foundValue ?: 0 as T
    }

    override fun getOrThrow(): T {
        val foundValue = get(null)
        return foundValue ?: throw produceErrorNoData()
    }

}