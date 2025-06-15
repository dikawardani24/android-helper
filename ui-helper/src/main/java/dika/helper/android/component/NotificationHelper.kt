package dika.helper.android.component

import android.app.PendingIntent
import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import dika.helper.android.component.custom.notification.AppNotification
import dika.helper.android.component.custom.notification.AppNotificationContent
import dika.helper.android.component.custom.notification.AppNotificationIconConfig
import dika.helper.android.component.custom.notification.AppNotificationStyle
import dika.helper.android.res.getColorFromRes

object NotificationHelper {

    fun sendNotification(
        context: Context,
        channelId: String,
        iconConfig: AppNotificationIconConfig,
        content: AppNotificationContent,
        style: AppNotificationStyle,
        pendingIntent: PendingIntent? = null
    ) {
        val notification = AppNotification(
            context = context,
            channelId = channelId,
            iconConfig = iconConfig,
            content = content,
            style = style,
            pendingIntent = pendingIntent
        )
        notification.sendNotification()
    }

}