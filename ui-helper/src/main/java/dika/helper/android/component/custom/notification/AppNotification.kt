package dika.helper.android.component.custom.notification

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dika.helper.android.permission.checkHasPermission

class AppNotification(
    private val context: Context,
    private val channelId: String,
    private val iconConfig: AppNotificationIconConfig,
    private val content: AppNotificationContent,
    private val style: AppNotificationStyle,
    private val pendingIntent: PendingIntent? = null
) {
    private fun createNotification() = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(iconConfig.smallIconCompat)
        .setLargeIcon(iconConfig.getLargeIcon(context))
        .setAutoCancel(style.autoCancel)
        .setSound(style.sound)
        .setColor(style.color)
        .setStyle(style.notificationStyle)
        .setContentTitle(content.title)
        .setContentText(content.message)
        .setSubText(content.subTitle)
        .setContentIntent(pendingIntent)
        .build()

    fun sendNotification() {
        val notification = createNotification()
        val notificationManager = NotificationManagerCompat.from(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!context.checkHasPermission(Manifest.permission.POST_NOTIFICATIONS)) {
                return
            }
        }
        notificationManager.notify(0, notification)
    }
}