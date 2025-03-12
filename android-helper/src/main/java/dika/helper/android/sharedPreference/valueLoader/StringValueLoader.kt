package dika.helper.android.sharedPreference.valueLoader

import android.content.SharedPreferences

class StringValueLoader(sharedPreferences: SharedPreferences) : BaseValueLoader<String>(sharedPreferences) {

    override fun get(key: String, default: String?): String? {
        return sharedPreferences.getString(key, default)
    }

    override fun getOrEmpty(key: String): String {
        val foundValue = get(key, "")
        return foundValue ?: ""    }

    override fun getOrThrow(key: String): String {
        val foundValue = get(key, null)
        return foundValue ?: throw produceErrorNoData(key)
    }

}