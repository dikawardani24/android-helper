package digital.klik.helper.dataStore

import android.content.Context
import digital.klik.helper.dataStore.sharedPreference.SharePreferenceDataSource
import digital.klik.helper.dataStore.sharedPreference.SharePreferenceDataSourceImpl

@Suppress("unused")
object DataStoreFactory {

    fun getSharePreferenceDataSource(context: Context, preferenceName: String, mode: Int): SharePreferenceDataSource {
        return SharePreferenceDataSourceImpl(
            context = context,
            mode = mode,
            preferenceName = preferenceName
        )
    }

    fun getPrivateSharePreferenceDataSource(context: Context, preferenceName: String): SharePreferenceDataSource {
        return SharePreferenceDataSourceImpl(
            context = context,
            mode = Context.MODE_PRIVATE,
            preferenceName = preferenceName
        )
    }

}