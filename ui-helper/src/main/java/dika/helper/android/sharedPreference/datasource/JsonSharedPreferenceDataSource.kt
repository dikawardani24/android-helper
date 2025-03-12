package dika.helper.android.sharedPreference.datasource

import dika.helper.android.api.extension.fromJson
import dika.helper.android.api.extension.toJson
import dika.helper.android.sharedPreference.SharePreferenceProvider
import dika.helper.android.sharedPreference.exception.SharedPreferenceException

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
            sharePreferenceProvider.remove(key)
        }
    }
}