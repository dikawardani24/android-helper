package digital.klik.helper.sharedPreference

import digital.klik.helper.sharedPreference.valueLoader.ValueLoader

interface SharePreferenceProvider {

    fun remove(key: String)

    fun store(key: String, value: Any)

    fun getStringValueLoader(key: String): ValueLoader<String>

    fun getIntValueLoader(key: String): ValueLoader<Int>

    fun getLongValueLoader(key: String): ValueLoader<Long>

    fun getFloatValueLoader(key: String): ValueLoader<Float>

    fun getStringSetValueLoader(key: String): ValueLoader<Set<String>>
}
