package digital.klik.helper.sharedPreference.valueLoader

import android.content.SharedPreferences

class StringSetValueLoader(sharedPreferences: SharedPreferences, key: String) : BaseValueLoader<Set<String>>(sharedPreferences, key) {
    override fun get(default: Set<String>?): Set<String>? {
        return sharedPreferences.getStringSet(key, default)
    }

    override fun getOrEmpty(): Set<String> {
        val foundValue =  get(null)
        return foundValue ?: emptySet()
    }

    override fun getOrThrow(): Set<String> {
        val foundValue = get(null)
        return foundValue ?: throw produceErrorNoData()
    }
}
