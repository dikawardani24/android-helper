package digital.klik.helper.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import digital.klik.helper.android.exception.*

@Suppress("MemberVisibilityCanBePrivate", "unused")
object DeviceHelper {

    private fun getServiceTelephonyManager(context: Context): TelephonyManager {
        val permission = Manifest.permission.READ_PHONE_STATE
        val readPhoneStateGranted:Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }

        if (readPhoneStateGranted) {
            try {
                val serviceName = Context.TELEPHONY_SERVICE
                val service = context.getSystemService(serviceName)
                if (service is TelephonyManager) {
                    return service
                } else {
                    throw ServiceException("Service of $serviceName is not type of ${TelephonyManager::class}, the service is $service")
                }
            } catch (e: Exception) {
                throw AndroidException("${e.message}", e)
            }
        } else {
            throw PermissionException("Permission of $permission is not granted")
        }
    }

    fun getIMEIOrThrow(context: Context): String {
        val service = getServiceTelephonyManager(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val imei = service.imei
            return if (imei.isNotEmpty()) imei else throw IMEIException("IMEI is not available on ${Build.VERSION.SDK_INT}")
        } else {
            throw ServiceException("Unable to load imei device for sdk version ${Build.VERSION.SDK_INT}")
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
    fun getDeviceIdOrThrow(context: Context): String {
        val service = getServiceTelephonyManager(context)
        val deviceID = service.deviceId
        return if (deviceID.isNotEmpty()) deviceID; else throw DeviceIdException("Device ID is not available on ${Build.VERSION.SDK_INT}")
    }

    fun getDeviceIdOrDefault(context: Context, default: String): String {
        return try {
            getIMEIOrThrow(context)
        } catch (e: Exception) {
            default
        }
    }

    fun getIMEIorDeviceID(context: Context, default: String): String {
        val imei = getIMEIOrDefault(context, "")
        return if (imei.isEmpty()) {
            val deviceId = getDeviceIdOrDefault(context, "")
            if (deviceId.isEmpty()) default else deviceId
        } else {
            imei
        }
    }
}