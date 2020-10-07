package digital.klik.helper.sharedPreference.valueLoader

import android.content.SharedPreferences

class LongValueLoader(sharedPreferences: SharedPreferences) : NumberValueLoader<Long>(sharedPreferences) {

    override fun get(key: String, default: Long?): Long? {
        val defaultToReturn = default ?: invalidValue.toLong()

        val foundValue = sharedPreferences.getLong(key, defaultToReturn)
        return if (foundValue == defaultToReturn) {
            default
        } else {
            foundValue
        }
    }
}