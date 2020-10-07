package digital.klik.helper.dataStore.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import digital.klik.helper.dataStore.sharedPreference.valueLoader.*
import digital.klik.helper.dataStore.sharedPreference.exception.SharedPreferenceException

@Suppress("unused", "MemberVisibilityCanBePrivate")
class SharePreferenceDataSourceImpl(context: Context, mode: Int, preferenceName: String):
    SharePreferenceDataSource {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(preferenceName, mode)

    override fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    override fun store(key: String, value: Any) {
        val editor = sharedPreferences.edit()

        when(value) {
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Int -> editor.putInt(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            is Set<*> -> {
                if (value.filterIsInstance(String::class.java).isNotEmpty()) {
                    editor.putStringSet(key, value as Set<String>)
                }
            }
            else -> throw SharedPreferenceException(
                "Unsupported type for value $value"
            )
        }
        editor.apply()
    }

    override fun getStringValueLoader(): ValueLoader<String> = StringValueLoader(sharedPreferences)

    override fun getIntValueLoader(): ValueLoader<Int> = IntegerValueLoader(sharedPreferences)

    override fun getLongValueLoader(): ValueLoader<Long> = LongValueLoader(sharedPreferences)

    override fun getFloatValueLoader(): ValueLoader<Float> = FloatValueLoader(sharedPreferences)

    override fun getStringSetValueLoader(): ValueLoader<Set<String>> = StringSetValueLoader(sharedPreferences)

    override fun getString(key: String, default: String?): String? = getStringValueLoader().get(key, default)

    override fun getInt(key: String, default: Int?): Int? = getIntValueLoader().get(key, default)

    override fun getLong(key: String, default: Long?): Long? = getLongValueLoader().get(key, default)

    override fun getFloat(key: String, default: Float?): Float? = getFloatValueLoader().get(key, default)

    override fun getStringSet(key: String, default: Set<String>?): Set<String>? = getStringSetValueLoader().get(key, default)
}