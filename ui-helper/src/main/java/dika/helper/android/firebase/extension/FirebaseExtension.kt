package dika.helper.android.firebase.extension

import android.app.Application
import dika.helper.android.firebase.FirebaseHelper

fun Application.initFirebase() {
    FirebaseHelper.initFirebase(this)
}