package dika.helper.android.firebase

import android.app.Application
import com.google.firebase.FirebaseApp
import dika.helper.android.firebase.exception.FirebaseException

@Suppress("unused")
object FirebaseHelper {

    fun initFirebase(application: Application) {
        try {
            FirebaseApp.initializeApp(application)
        } catch (e: Exception) {
            throw FirebaseException(e.message, e)
        }
    }
}