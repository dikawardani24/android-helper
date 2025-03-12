package dika.helper.android

import android.app.Activity
import androidx.fragment.app.Fragment
import dika.helper.android.exception.AppException

fun Activity.getKDigiApplication(): KlikDigitalApplication {
    val app = application
    if (app is KlikDigitalApplication) {
        return app
    } else {
        throw AppException("The application container of ${app::class.simpleName} is not type of ${KlikDigitalApplication::class.simpleName}")
    }
}

fun Fragment.getKDigiApplication(): KlikDigitalApplication {
    return requireActivity().getKDigiApplication()
}