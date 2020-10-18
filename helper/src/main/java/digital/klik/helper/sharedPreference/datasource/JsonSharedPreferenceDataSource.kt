package digital.klik.helper.sharedPreference.datasource

import digital.klik.helper.api.extension.fromJson
import digital.klik.helper.api.extension.toJson
import digital.klik.helper.sharedPreference.SharePreferenceProvider
import digital.klik.helper.sharedPreference.exception.SharedPreferenceException

@Suppress("MemberVisibilityCanBePrivate", "unused")
open class JsonSharedPreferenceDataSource<T: Any>(
    private val sharePreferenceProvider: SharePreferenceProvider,
    private val aClass: Class<T>
): SharedPreferenceDataSource<T> {
    private val key = aClass.simpleName

    override fun store(data: T) {
        val dataInJson = data.toJson()
        sharePreferenceProvider.store(key, dataInJson)
    }

    override fun getStoredData(): T {
        val dataInJson = sharePreferenceProvider.getStringOrThrow(key)
        return dataInJson.fromJson(aClass)
    }

    override fun getStoredDataOrNull(): T? {
        return try {
            getStoredData()
        } catch (e: SharedPreferenceException) {
            null
        }
    }

    override fun clearStoredData() {
        val dataInJson = sharePreferenceProvider.getStringOrEmpty(key)
        if (dataInJson.isNotEmpty()) {
            sharePreferenceProvider.store(key, "")
        }
    }
}