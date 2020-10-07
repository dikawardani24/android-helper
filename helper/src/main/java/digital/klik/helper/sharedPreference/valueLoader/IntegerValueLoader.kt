package digital.klik.helper.sharedPreference.valueLoader

import android.content.SharedPreferences

class IntegerValueLoader (sharedPreferences: SharedPreferences): NumberValueLoader<Int>(sharedPreferences) {

    override fun get(key: String, default: Int?): Int? {
        val defaultToReturn = default ?: invalidValue

        val foundValue = sharedPreferences.getInt(key, defaultToReturn)
        return if (foundValue == defaultToReturn) {
            default
        } else {
            foundValue
        }
    }
}