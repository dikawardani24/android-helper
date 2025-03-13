package dika.helper.android

import android.app.Activity
import androidx.fragment.app.Fragment
import dika.helper.core.exception.AppException

fun Activity.getDikaApplication(): DikaApplication {
    val app = application
    if (app is DikaApplication) {
        return app
    } else {
        throw AppException("The application container of ${app::class.simpleName} is not type of ${DikaApplication::class.simpleName}")
    }
}

fun Fragment.getDikaApplication(): DikaApplication {
    return requireActivity().getDikaApplication()
}