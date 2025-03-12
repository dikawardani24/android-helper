package dika.helper.android.sharedPreference.valueLoader

import android.content.SharedPreferences

abstract class NumberValueLoader<T: Number>(sharedPreferences: SharedPreferences): BaseValueLoader<T>(sharedPreferences) {
    protected val invalidValue = -1

    @Suppress("UNCHECKED_CAST")
    override fun getOrEmpty(key: String): T {
        val foundValue = get(key, null)
        return foundValue ?: 0 as T
    }

    override fun getOrThrow(key: String): T {
        val foundValue = get(key, null)
        return foundValue ?: throw produceErrorNoData(key)
    }

}