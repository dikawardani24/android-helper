package digital.klik.helper.firebase.extension

import android.app.Application
import digital.klik.helper.firebase.FirebaseHelper

fun Application.initFirebase() {
    FirebaseHelper.initFirebase(this)
}