package digital.klik.helper

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import digital.klik.helper.sharedPreference.SharePreferenceDataSource
import digital.klik.helper.sharedPreference.SharePreferenceDataSourceImpl

class KlikDigitalApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }

    fun getSharedPreferenceOpenHelper(preferenceName: String, mode: Int): SharePreferenceDataSource {
        return SharePreferenceDataSourceImpl(
            context = this,
            mode = mode,
            preferenceName = preferenceName
        )
    }

    companion object {
        private val TAG = KlikDigitalApplication::class.simpleName
    }
}