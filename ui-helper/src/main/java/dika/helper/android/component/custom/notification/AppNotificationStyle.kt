package dika.helper.android.component.custom.notification

import android.net.Uri
import androidx.annotation.ColorRes
import androidx.core.app.NotificationCompat

data class AppNotificationStyle(
    @ColorRes val color: Int,
    val sound: Uri? = null,
    val notificationStyle: NotificationCompat.Style? = null,
    val autoCancel: Boolean = true,
)