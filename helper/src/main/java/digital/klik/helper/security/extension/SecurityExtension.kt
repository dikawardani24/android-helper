package digital.klik.helper.security.extension

import android.app.Activity
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import digital.klik.helper.security.exception.SecurityException


fun Activity.installProviderIfNeeded() {
    try {
        ProviderInstaller.installIfNeeded(this)
    } catch (e: GooglePlayServicesRepairableException) {
        throw SecurityException("Unable to provider cause by ${e.message}", e)
    } catch (e: GooglePlayServicesNotAvailableException) {
        throw SecurityException("Unable to provider cause by ${e.message}", e)
    }
}