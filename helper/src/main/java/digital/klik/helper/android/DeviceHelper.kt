package digital.klik.helper.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import digital.klik.helper.android.exception.AndroidException
import digital.klik.helper.android.exception.PermissionException
import digital.klik.helper.android.exception.ServiceException

@Suppress("MemberVisibilityCanBePrivate", "unused")
object DeviceHelper {

    private fun getServiceTelephonyManager(context: Context): TelephonyManager {
        try {
            val permission = Manifest.permission.READ_PHONE_STATE
            val readPhoneStateGranted = ActivityCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED

            if (readPhoneStateGranted) {
                val serviceName = Context.TELECOM_SERVICE
                val service = context.getSystemService(serviceName)
                if (service is TelephonyManager) {
                    return service
                } else {
                    throw ServiceException("Service of $serviceName is not type of ${TelephonyManager::class.simpleName}")
                }
            } else {
                throw PermissionException("Permission of $permission is not granted")
            }
        } catch (e: Exception) {
            throw AndroidException(e.message, e)
        }
    }

    fun getIMEIOrThrow(context: Context): String {
        val service = getServiceTelephonyManager(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return service.imei
        } else {
            throw ServiceException("Unable to load imei device for sak version ${Build.VERSION.SDK_INT}")
        }
    }

    fun getIMEIOrDefault(context: Context, default: String): String {
        return try {
            getIMEIOrThrow(context)
        } catch (e: Exception) {
            default
        }
    }

    @Suppress("DEPRECATION")
    @SuppressLint("HardwareIds")
    fun getDeviceIdOrTrhow(context: Context): String {
        val service = getServiceTelephonyManager(context)
        return service.deviceId
    }

    fun getDeviceIdOrDefault(context: Context, default: String): String {
        return try {
            getIMEIOrThrow(context)
        } catch (e: Exception) {
            default
        }
    }
}