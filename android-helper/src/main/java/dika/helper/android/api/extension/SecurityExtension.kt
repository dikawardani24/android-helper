package dika.helper.android.api.extension

import android.content.Context
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import dika.helper.android.security.exception.SecurityException


fun Context.installProviderIfNeeded() {
    try {
        ProviderInstaller.installIfNeeded(this)
    } catch (e: GooglePlayServicesRepairableException) {
        throw SecurityException("Unable to provider cause by ${e.message}", e)
    } catch (e: GooglePlayServicesNotAvailableException) {
        throw SecurityException("Unable to provider cause by ${e.message}", e)
    }
}