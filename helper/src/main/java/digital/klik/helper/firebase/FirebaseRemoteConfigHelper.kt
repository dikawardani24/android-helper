package digital.klik.helper.firebase

import androidx.annotation.XmlRes
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import digital.klik.helper.Result
import digital.klik.helper.firebase.exception.FirebaseException

object FirebaseRemoteConfigHelper {

    fun fetchRemoteConfig(
        minimumFetchInterval: Long,
        @XmlRes resCinfigDefaults: Int,
        additionalSetting: (builder: FirebaseRemoteConfigSettings.Builder) -> Unit,
        resultHandler: (result: Result<FirebaseRemoteConfig>) -> Unit
    ) {
        val builder = FirebaseRemoteConfigSettings.Builder()
        additionalSetting.invoke(builder)

        val remoteConfig = FirebaseRemoteConfig.getInstance().apply {
            val settings = builder.build()
            setConfigSettingsAsync(settings)
            setDefaultsAsync(resCinfigDefaults)
        }

        val task = remoteConfig.fetch(minimumFetchInterval)
        task.addOnCompleteListener {
            if (it.isSuccessful) {
                resultHandler(Result.Success(remoteConfig))
            } else {
                val error = FirebaseException("Unable to load config from firebase cause by task the task is not successful")
                resultHandler(Result.Failure(error))
            }
        }.addOnFailureListener {
            resultHandler(Result.Failure(it))
        }
    }

}