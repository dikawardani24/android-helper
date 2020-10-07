package digital.klik.helper.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import digital.klik.helper.sharedPreference.valueLoader.*
import digital.klik.helper.sharedPreference.exception.SharedPreferenceException

@Suppress("unused", "MemberVisibilityCanBePrivate")
class SharePreferenceProviderImpl(context: Context, mode: Int, preferenceName: String):
    SharePreferenceProvider {
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

    override fun getStringValueLoader(key: String): ValueLoader<String> = StringValueLoader(sharedPreferences, key)

    override fun getIntValueLoader(key: String): ValueLoader<Int> = IntegerValueLoader(sharedPreferences, key)

    override fun getLongValueLoader(key: String): ValueLoader<Long> = LongValueLoader(sharedPreferences, key)

    override fun getFloatValueLoader(key: String): ValueLoader<Float> = FloatValueLoader(sharedPreferences, key)

    override fun getStringSetValueLoader(key: String): ValueLoader<Set<String>> = StringSetValueLoader(sharedPreferences, key)
}