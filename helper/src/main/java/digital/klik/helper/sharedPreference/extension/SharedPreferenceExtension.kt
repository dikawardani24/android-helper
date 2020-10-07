package digital.klik.helper.sharedPreference.extension

import android.app.Activity
import android.content.Context
import digital.klik.helper.sharedPreference.SharePreferenceProvider
import digital.klik.helper.sharedPreference.SharePreferenceProviderImpl

fun Activity.getSharedPreferenceProvider(preferenceName: String, mode: Int): SharePreferenceProvider {
    return SharePreferenceProviderImpl(
        context = this,
        mode = mode,
        preferenceName = preferenceName
    )
}

fun Activity.getPrivateSharedPreferenceProvider(preferenceName: String): SharePreferenceProvider {
    return getSharedPreferenceProvider(preferenceName, Context.MODE_PRIVATE)
}
