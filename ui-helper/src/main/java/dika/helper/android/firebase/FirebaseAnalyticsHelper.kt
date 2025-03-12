package dika.helper.android.firebase

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

@Suppress("unused")
object FirebaseAnalyticsHelper {

    private fun getInstance(context: Context, additionalOption: FirebaseAnalytics.() -> Unit = {}): FirebaseAnalytics {
        val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
        firebaseAnalytics.additionalOption()
        return firebaseAnalytics
    }

    fun sendLogEvent(context: Context, event: String, dataToSendCollector: Bundle.() -> Unit) {
        val bundle = Bundle()
        bundle.dataToSendCollector()

        val firebaseAnalytics = getInstance(context)
        firebaseAnalytics.logEvent(event, bundle)
    }
}