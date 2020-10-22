package digital.klik.helper.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import digital.klik.helper.common.InstanceHelper
import digital.klik.helper.sharedPreference.valueLoader.*
import digital.klik.helper.sharedPreference.exception.SharedPreferenceException

@Suppress("unused", "MemberVisibilityCanBePrivate")
class SharePreferenceProviderImpl(
    private val sharedPreferences: SharedPreferences
): SharePreferenceProvider {
    private val stringValueLoader: ValueLoader<String> = StringValueLoader(sharedPreferences)
    private val longValueLoader: ValueLoader<Long> = LongValueLoader(sharedPreferences)
    private val intValueLoader: ValueLoader<Int> = IntegerValueLoader(sharedPreferences)
    private val floatValueLoader: ValueLoader<Float> = FloatValueLoader(sharedPreferences)
    private val stringSetValueLoader: ValueLoader<Set<String>> = StringSetValueLoader(sharedPreferences)

    constructor(context: Context, preferenceName: String): this(context, Context.MODE_PRIVATE, preferenceName)

    constructor(context: Context, mode: Int, preferenceName: String): this(context.getSharedPreferences(preferenceName, mode))

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
                InstanceHelper.cast<Set<String>>(value) {
                    editor.putStringSet(key, this)
                }
            }
            else -> throw SharedPreferenceException(
                "Unsupported type for value $value"
            )
        }
        editor.apply()
    }

    override fun getString(key: String, default: String?): String? = stringValueLoader.get(key, default)

    override fun getStringOrEmpty(key: String): String = stringValueLoader.getOrEmpty(key)

    override fun getStringOrThrow(key: String): String = stringValueLoader.getOrThrow(key)

    override fun getInt(key: String, default: Int?): Int? = intValueLoader.get(key, default)

    override fun getIntOrEmpty(key: String): Int = intValueLoader.getOrEmpty(key)

    override fun getIntOrThrow(key: String): Int = intValueLoader.getOrThrow(key)

    override fun getFloat(key: String, default: Float?): Float? = floatValueLoader.get(key, default)

    override fun getFloatOrEmpty(key: String): Float = floatValueLoader.getOrEmpty(key)

    override fun getFloatOrThrow(key: String): Float = floatValueLoader.getOrThrow(key)

    override fun getLong(key: String, default: Long?): Long? = longValueLoader.get(key, default)

    override fun getLongOrEmpty(key: String): Long = longValueLoader.getOrEmpty(key)

    override fun getLongOrThrow(key: String): Long = longValueLoader.getOrThrow(key)

    override fun getStringSet(key: String, default: Set<String>?): Set<String>? = stringSetValueLoader.get(key, default)

    override fun getStringSetOrEmpty(key: String): Set<String> = stringSetValueLoader.getOrEmpty(key)

    override fun getStringSetOrThrow(key: String): Set<String> = stringSetValueLoader.getOrThrow(key)
}