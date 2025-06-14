package dika.helper.android.component

import android.content.Context
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
        @DrawableRes smallIconRes: Int,
        @ColorRes color: Int,
        title: String,
        message: String
    ) {
        val notification = AppNotification(
            context = context,
            channelId = channelId,
            iconConfig = AppNotificationIconConfig.from(
                context = context,
                smallIconRes = smallIconRes,
                largeIconRes = -1
            ),
            content = AppNotificationContent(
                title = title,
                message = message
            ),
            style = AppNotificationStyle(context.getColorFromRes(color))
        )
        notification.sendNotification()
    }

}