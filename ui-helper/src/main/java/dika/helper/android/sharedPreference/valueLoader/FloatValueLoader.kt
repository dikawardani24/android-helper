package dika.helper.android.sharedPreference.valueLoader

import android.content.SharedPreferences

class FloatValueLoader(sharedPreferences: SharedPreferences) : NumberValueLoader<Float>(sharedPreferences) {

    override fun get(key: String, default: Float?): Float? {
        val defaultToReturn = default ?: invalidValue.toFloat()

        val foundValue = sharedPreferences.getFloat(key, defaultToReturn)
        return if (foundValue == defaultToReturn) {
            default
        } else {
            foundValue
        }
    }
}