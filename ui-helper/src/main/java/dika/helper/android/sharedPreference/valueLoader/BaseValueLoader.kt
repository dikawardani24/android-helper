package dika.helper.android.sharedPreference.valueLoader

import android.content.SharedPreferences
import dika.helper.android.sharedPreference.exception.SharedPreferenceException

abstract class BaseValueLoader<T>(
    protected val sharedPreferences: SharedPreferences
): ValueLoader<T> {

    protected fun produceErrorNoData(key: String): SharedPreferenceException {
        return SharedPreferenceException(
            "Data for key $key doesn't exist"
        )
    }

}