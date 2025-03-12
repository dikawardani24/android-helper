package dika.helper.android.security.extension

import android.content.Context
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import dika.helper.api.exception.ConnectException

fun Context.installProviderIfNeeded() {
    try {
        ProviderInstaller.installIfNeeded(this)
    } catch (e: GooglePlayServicesRepairableException) {
        throw ConnectException("Unable to provider cause by ${e.message}", e)
    } catch (e: GooglePlayServicesNotAvailableException) {
        throw ConnectException("Unable to provider cause by ${e.message}", e)
    }
}