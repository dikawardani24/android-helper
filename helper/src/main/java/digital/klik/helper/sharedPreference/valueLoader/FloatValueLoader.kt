package digital.klik.helper.sharedPreference.valueLoader

import android.content.SharedPreferences

class FloatValueLoader(sharedPreferences: SharedPreferences, key: String) : NumberValueLoader<Float>(sharedPreferences, key) {

    override fun get(default: Float?): Float? {
        val defaultToReturn = default ?: invalidValue.toFloat()

        val foundValue = sharedPreferences.getFloat(key, defaultToReturn)
        return if (foundValue == defaultToReturn) {
            default
        } else {
            foundValue
        }
    }
}