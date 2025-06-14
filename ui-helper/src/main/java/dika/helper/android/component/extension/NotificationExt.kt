package dika.helper.android.component.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import dika.helper.android.component.NotificationHelper

fun Context.sendNotification(
    channelId: String,
    @DrawableRes smallIconRes: Int,
    @ColorRes color: Int,
    title: String,
    message: String
) = NotificationHelper.sendNotification(
    context = this,
    channelId = channelId,
    smallIconRes = smallIconRes,
    color = color,
    title = title,
    message = message
)