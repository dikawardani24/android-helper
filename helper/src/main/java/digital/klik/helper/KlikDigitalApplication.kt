package digital.klik.helper

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import digital.klik.helper.sharedPreference.SharePreferenceProvider
import digital.klik.helper.sharedPreference.SharePreferenceProviderImpl

class KlikDigitalApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }

    fun getSharedPreferenceProvider(preferenceName: String, mode: Int): SharePreferenceProvider {
        return SharePreferenceProviderImpl(
            context = this,
            mode = mode,
            preferenceName = preferenceName
        )
    }

    fun getPrivateSharedPreferenceProvider(preferenceName: String): SharePreferenceProvider {
        return SharePreferenceProviderImpl(
            context = this,
            mode = Context.MODE_PRIVATE,
            preferenceName = preferenceName
        )
    }

    companion object {
        private val TAG = KlikDigitalApplication::class.simpleName
    }
}