package digital.klik.helper.dataStore.sharedPreference.valueLoader

import android.content.SharedPreferences
import digital.klik.helper.exception.SharedPreferenceException

abstract class BaseValueLoader<T>(protected val sharedPreferences: SharedPreferences): ValueLoader<T> {

    protected fun produceErrorNoData(key: String): SharedPreferenceException {
        return SharedPreferenceException("Data for key $key doesn't exist")
    }

}