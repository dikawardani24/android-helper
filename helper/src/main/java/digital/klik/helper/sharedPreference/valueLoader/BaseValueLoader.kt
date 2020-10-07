package digital.klik.helper.sharedPreference.valueLoader

import android.content.SharedPreferences
import digital.klik.helper.sharedPreference.exception.SharedPreferenceException

abstract class BaseValueLoader<T>(
    protected val sharedPreferences: SharedPreferences,
    protected val key: String
): ValueLoader<T> {

    protected fun produceErrorNoData(): SharedPreferenceException {
        return SharedPreferenceException(
            "Data for key $key doesn't exist"
        )
    }

}