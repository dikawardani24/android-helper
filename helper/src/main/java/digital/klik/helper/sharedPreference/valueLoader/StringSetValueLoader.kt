package digital.klik.helper.sharedPreference.valueLoader

import android.content.SharedPreferences

class StringSetValueLoader(sharedPreferences: SharedPreferences) : BaseValueLoader<Set<String>>(sharedPreferences) {
    override fun get(key: String, default: Set<String>?): Set<String>? {
        return sharedPreferences.getStringSet(key, default)
    }

    override fun getOrEmpty(key: String): Set<String> {
        val foundValue =  get(key, null)
        return foundValue ?: emptySet()
    }

    override fun getOrThrow(key: String): Set<String> {
        val foundValue = get(key, null)
        return foundValue ?: throw produceErrorNoData(key)
    }
}
