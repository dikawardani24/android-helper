package digital.klik.helper.sharedPreference

import digital.klik.helper.sharedPreference.valueLoader.ValueLoader

interface SharePreferenceDataSource {

    fun remove(key: String)

    fun store(key: String, value: Any)

    fun getStringValueLoader(): ValueLoader<String>

    fun getIntValueLoader(): ValueLoader<Int>

    fun getLongValueLoader(): ValueLoader<Long>

    fun getFloatValueLoader(): ValueLoader<Float>

    fun getStringSetValueLoader(): ValueLoader<Set<String>>

    fun getString(key: String, default: String?): String?

    fun getInt(key: String, default: Int?): Int?

    fun getLong(key: String, default: Long?): Long?

    fun getFloat(key: String, default: Float?): Float?

    fun getStringSet(key: String, default: Set<String>?): Set<String>?
}
