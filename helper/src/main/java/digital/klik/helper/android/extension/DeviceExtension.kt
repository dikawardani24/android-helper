@file:Suppress("unused")

package digital.klik.helper.android.extension

import android.content.Context
import androidx.fragment.app.Fragment
import digital.klik.helper.android.DeviceHelper

fun Context.getIMEIOrThrow(): String {
    return DeviceHelper.getIMEIOrThrow(this)
}

fun Context.getIMEIOrDefault(default: String): String {
    return DeviceHelper.getIMEIOrDefault(this, default)
}

fun Fragment.getIMEIOrThrow(): String {
    return DeviceHelper.getIMEIOrThrow(requireContext())
}

fun Fragment.getIMEIOrDefault(default: String): String {
    return DeviceHelper.getIMEIOrDefault(requireContext(), default)
}

fun Context.getDeviceIdOrThrow(): String {
    return DeviceHelper.getDeviceIdOrThrow(this)
}

fun Context.getDeviceIdOrDefault(default: String): String {
    return DeviceHelper.getDeviceIdOrDefault(this, default)
}

fun Fragment.getDeviceIdOrThrow(): String {
    return DeviceHelper.getIMEIOrThrow(requireContext())
}

fun Fragment.getDeviceIdOrDefault(default: String): String {
    return DeviceHelper.getIMEIOrDefault(requireContext(), default)
}

fun Context.getIMEIorDeviceID(default: String): String {
    return DeviceHelper.getIMEIorDeviceID(this, default)
}

fun Fragment.getIMEIorDeviceID(default: String): String {
    return DeviceHelper.getIMEIorDeviceID(requireContext(), default)
}