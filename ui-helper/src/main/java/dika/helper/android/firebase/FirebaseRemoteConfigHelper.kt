package dika.helper.android.firebase

import androidx.annotation.XmlRes
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dika.helper.core.Result
import dika.helper.android.firebase.exception.FirebaseException

object FirebaseRemoteConfigHelper {

    fun getInstance(
        @XmlRes resConfigDefaults: Int,
        additionalSetting: FirebaseRemoteConfigSettings.() -> Unit = {}
    ): FirebaseRemoteConfig {
        val settings = FirebaseRemoteConfigSettings.Builder().build()
        settings.additionalSetting()
        return FirebaseRemoteConfig.getInstance().apply {
            setConfigSettingsAsync(settings)
            setDefaultsAsync(resConfigDefaults)
        }
    }

    fun fetchRemoteConfig(
        minimumFetchInterval: Long,
        remoteConfig: FirebaseRemoteConfig,
        resultHandler: (result: Result<FirebaseRemoteConfig>) -> Unit
    ) {
        val task = remoteConfig.fetch(minimumFetchInterval)
        task.addOnCompleteListener {
            if (it.isSuccessful) {
                resultHandler(Result.Success(remoteConfig))
            } else {
                val taskError = it.exception
                val messageError = taskError?.message ?: "Unable to load config from firebase cause by task the task is not successful"
                val error = FirebaseException(messageError, taskError)
                resultHandler(Result.Failure(error))
            }
        }.addOnFailureListener {
            resultHandler(Result.Failure(it))
        }
    }

}