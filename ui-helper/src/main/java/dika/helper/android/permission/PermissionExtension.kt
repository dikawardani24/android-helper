@file:Suppress("unused")

package dika.helper.android.permission

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import dika.helper.core.exception.IllegalArgsException
import dika.helper.core.extension.logDebug

fun Activity.checkHasPermission(permission: String): Boolean {
    if (permission.isEmpty()) throw IllegalArgsException("Permission cannot be empty")
    val result = ContextCompat.checkSelfPermission(this, permission)
    val granted = result == PackageManager.PERMISSION_GRANTED
    logDebug("Permission : $permission, isGranted : $granted")
    return granted
}

fun Activity.checkHasPermissions(permissions: Array<out String>): Boolean {
    if (permissions.isEmpty()) throw IllegalArgsException("Permissions length must be greater than 0")
    permissions.forEach { permission ->
        if (!checkHasPermission(permission)) {
            logDebug("Check permission for $permissions : false")
            return false
        }
    }
    logDebug("Check permission for $permissions : true")
    return true
}

fun Activity.isPermissionGranted(
    requiredPermission: String,
    permissions: Array<out String>,
    grantResults: IntArray
): Boolean {
    if (requiredPermission.isEmpty()) throw IllegalArgsException("Required permission cannot be empty")
    if (permissions.isEmpty()) throw IllegalArgsException("Result permission cannot be empty")
    if (grantResults.isEmpty()) throw IllegalArgsException("Grant results cannot be empty")
    if (permissions.size != grantResults.size) throw IllegalArgsException("Permissions length must be equal to grant results length")

    permissions.forEachIndexed { index, permission ->
        if (permission == requiredPermission) {
            val granted = grantResults[index] == PackageManager.PERMISSION_GRANTED
            logDebug("Required permission: $requiredPermission, isGranted: $granted")
            return granted
        }
    }
    logDebug("Required permission: $requiredPermission, isGranted: false")
    return false
}

fun Activity.isPermissionsGranted(
    requiredPermissions: Array<out String>,
    permissions: Array<out String>,
    grantResults: IntArray
): Boolean {
    if (requiredPermissions.isEmpty()) throw IllegalArgsException("Required permission cannot be empty")
    if (permissions.isEmpty()) throw IllegalArgsException("Result permission cannot be empty")
    if (grantResults.isEmpty()) throw IllegalArgsException("Grant results cannot be empty")
    if (permissions.size != grantResults.size) throw IllegalArgsException("Permissions length must be equal to grant results length")

    requiredPermissions.forEach { requiredPermission ->
        if (!isPermissionGranted(requiredPermission, permissions, grantResults)) {
            logDebug("IsPermissions for $requiredPermissions : false")
            return false
        }
    }

    logDebug("IsPermissions for $requiredPermissions : true")
    return true
}
