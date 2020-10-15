package digital.klik.helper.firebase.extension

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import digital.klik.helper.firebase.FirebaseAnalyticsHelper

fun Context.sendFirebaseAnalyticsLogEvent(event: String, dataToSendCollector: Bundle.() -> Unit) {
    FirebaseAnalyticsHelper.sendLogEvent(this, event, dataToSendCollector)
}

fun Fragment.sendFirebaseAnalyticsLogEvent(event: String, dataToSendCollector: Bundle.() -> Unit) {
    requireActivity().application.sendFirebaseAnalyticsLogEvent(event, dataToSendCollector)
}

fun AndroidViewModel.sendFirebaseAnalyticsLogEvent(event: String, dataToSendCollector: Bundle.() -> Unit) {
    getApplication<Application>().sendFirebaseAnalyticsLogEvent(event, dataToSendCollector)
}