package digital.klik.helper.sharedPreference.datasource

interface JsonSharedPreferenceDataSource<T: Any> {
    fun store(data: T)
    fun getStoredData(): T
    fun getStoredDataOrNull(): T?
    fun clearStoredData()
}