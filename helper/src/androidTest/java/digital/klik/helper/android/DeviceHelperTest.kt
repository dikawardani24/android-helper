package digital.klik.helper.android

import android.Manifest
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.permission.PermissionRequester
import digital.klik.helper.android.exception.AndroidException
import digital.klik.helper.common.extension.logDebug
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class DeviceHelperTest {

    private val appContext = getInstrumentation().targetContext

    @Before
    fun grantPhonePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionRequester().apply {
                addPermissions(Manifest.permission.READ_PHONE_STATE)
            }
        }
    }

    @Test
    fun getIMEIOrThrow() {
        try {
            val imei = DeviceHelper.getIMEIOrThrow(appContext)
            logDebug("IMEI : $imei")
            assertTrue(imei.isNotEmpty())
        } catch (e: Exception) {
            logDebug("Error : ${e.message}")
            assertTrue(e is AndroidException)
        }
    }

    @Test
    fun getIMEIOrDefault() {
        val imei = DeviceHelper.getIMEIOrDefault(appContext, "")
        assertTrue(imei.isNotEmpty())
    }

    @Test
    fun getDeviceIdOrThrow() {
        try {
            val deviceId = DeviceHelper.getDeviceIdOrThrow(appContext)
            logDebug("Device ID : $deviceId")
            assertTrue(deviceId.isNotEmpty())
        } catch (e: Exception) {
            logDebug("${e.message}")
            assertTrue(e is AndroidException)
        }
    }

    @Test
    fun getDeviceIdOrDefault() {
        val deviceId = DeviceHelper.getDeviceIdOrDefault(appContext, "")
        assertTrue(deviceId.isNotEmpty())
    }

    @Test
    fun getIMEIorDeviceID() {
        val imeiOrDeviceId = DeviceHelper.getIMEIorDeviceID(appContext, "")
        assertTrue(imeiOrDeviceId.isNotEmpty())
    }
}