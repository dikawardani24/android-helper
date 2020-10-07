package digital.klik.helper

import android.app.Activity
import androidx.fragment.app.Fragment
import digital.klik.helper.exception.AppException

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