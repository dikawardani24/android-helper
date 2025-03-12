package dika.helper.android.sharedPreference.datasource

interface SharedPreferenceDataSource<T: Any> {
    fun store(data: T)
    fun getStoredData(): T
    fun getStoredDataOrNull(): T?
    fun clearStoredData()
}