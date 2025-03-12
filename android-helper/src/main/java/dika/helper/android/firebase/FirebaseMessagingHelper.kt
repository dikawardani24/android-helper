package dika.helper.android.firebase

import com.google.firebase.messaging.FirebaseMessaging

@Suppress("unused")
object FirebaseMessagingHelper {

    fun getInstance(additionalOption: FirebaseMessaging.() -> Unit = {}): FirebaseMessaging {
        val firebaseMessaging = FirebaseMessaging.getInstance()
        firebaseMessaging.additionalOption()
        return firebaseMessaging
    }
}