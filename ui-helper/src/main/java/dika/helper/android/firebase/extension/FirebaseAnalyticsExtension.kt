package dika.helper.android.firebase.extension

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import dika.helper.android.firebase.FirebaseAnalyticsHelper

fun Context.sendFirebaseAnalyticsLogEvent(event: String, dataToSendCollector: Bundle.() -> Unit) {
    FirebaseAnalyticsHelper.sendLogEvent(this, event, dataToSendCollector)
}

fun Fragment.sendFirebaseAnalyticsLogEvent(event: String, dataToSendCollector: Bundle.() -> Unit) {
    requireActivity().application.sendFirebaseAnalyticsLogEvent(event, dataToSendCollector)
}

fun AndroidViewModel.sendFirebaseAnalyticsLogEvent(event: String, dataToSendCollector: Bundle.() -> Unit) {
    getApplication<Application>().sendFirebaseAnalyticsLogEvent(event, dataToSendCollector)
}