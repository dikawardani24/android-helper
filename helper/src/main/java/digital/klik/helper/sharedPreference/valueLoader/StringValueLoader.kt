package digital.klik.helper.sharedPreference.valueLoader

import android.content.SharedPreferences

class StringValueLoader(sharedPreferences: SharedPreferences, key: String) : BaseValueLoader<String>(sharedPreferences, key) {

    override fun get(default: String?): String? {
        return sharedPreferences.getString(key, default)
    }

    override fun getOrEmpty(): String {
        val foundValue = get("")
        return foundValue ?: ""    }

    override fun getOrThrow(): String {
        val foundValue = get(null)
        return foundValue ?: throw produceErrorNoData()
    }

}