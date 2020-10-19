package digital.klik.helper.sharedPreference.extension

import android.content.Context
import androidx.fragment.app.Fragment
import digital.klik.helper.sharedPreference.SharePreferenceProvider
import digital.klik.helper.sharedPreference.SharePreferenceProviderImpl

fun Context.getSharedPreferenceProvider(preferenceName: String, mode: Int): SharePreferenceProvider {
    return SharePreferenceProviderImpl(
        context = this,
        mode = mode,
        preferenceName = preferenceName
    )
}

fun Context.getPrivateSharedPreferenceProvider(preferenceName: String): SharePreferenceProvider {
    return getSharedPreferenceProvider(preferenceName, Context.MODE_PRIVATE)
}

fun Fragment.getSharedPreferenceProvider(preferenceName: String, mode: Int): SharePreferenceProvider {
    return SharePreferenceProviderImpl(
        context = requireContext(),
        mode = mode,
        preferenceName = preferenceName
    )
}

fun Fragment.getPrivateSharedPreferenceProvider(preferenceName: String): SharePreferenceProvider {
    return getSharedPreferenceProvider(preferenceName, Context.MODE_PRIVATE)
}