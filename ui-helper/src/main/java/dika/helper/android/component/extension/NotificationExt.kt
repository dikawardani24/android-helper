package dika.helper.android.component.extension

import android.app.PendingIntent
import android.content.Context
import android.net.Uri
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import dika.helper.android.component.NotificationHelper
import dika.helper.android.component.custom.notification.AppNotificationContent
import dika.helper.android.component.custom.notification.AppNotificationIconConfig
import dika.helper.android.component.custom.notification.AppNotificationStyle
import dika.helper.android.res.getColorFromRes

fun Context.sendNotification(
    channelId: String,
    @DrawableRes smallIconRes: Int,
    @ColorInt color: Int,
    title: String,
    message: String,
    subText: String? = null,
    sound: Uri? = null,
    style: NotificationCompat.Style ?= null,
    pendingIntent: PendingIntent? = null
) {
    NotificationHelper.sendNotification(
        context = this,
        channelId = channelId,
        iconConfig = AppNotificationIconConfig.from(this, smallIconRes = smallIconRes, largeIconRes = -1),
        style = AppNotificationStyle(
            color = color,
            sound = sound,
            notificationStyle = style,
            autoCancel = true
        ),
        content = AppNotificationContent(
            title = title,
            message = message,
            subTitle = subText
        ),
        pendingIntent = pendingIntent
    )
}