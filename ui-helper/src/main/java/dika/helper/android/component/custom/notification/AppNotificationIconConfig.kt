package dika.helper.android.component.custom.notification

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.IconCompat
import dika.helper.android.res.toIcon
import dika.helper.android.res.toIconOrNUll
import dika.helper.android.res.toIconOrNull

data class AppNotificationIconConfig(
    val smallIconCompat: IconCompat,
    val largeIconCompat: IconCompat? = null,
) {
    fun getLargeIcon(context: Context) = largeIconCompat?.toIcon(context)
    fun getSmallIcon(context: Context) = smallIconCompat.toIcon(context)

    companion object {
        fun from(context: Context, @DrawableRes smallIconRes: Int, @DrawableRes largeIconRes: Int = -1) = AppNotificationIconConfig(
            smallIconCompat = context.toIcon(smallIconRes),
            largeIconCompat = context.toIconOrNUll(largeIconRes)
        )

        fun from(context: Context, @DrawableRes smallIconRes: Int, largeIconBitmap: Bitmap? = null) = AppNotificationIconConfig(
            smallIconCompat = context.toIcon(smallIconRes),
            largeIconCompat = largeIconBitmap.toIconOrNull()
        )

        fun from(context: Context, @DrawableRes smallIconRes: Int, largeIconBitmap: Uri? = null) = AppNotificationIconConfig(
            smallIconCompat = context.toIcon(smallIconRes),
            largeIconCompat = largeIconBitmap.toIconOrNull()
        )

        fun from(context: Context, smallIconRes: Bitmap, @DrawableRes largeIconRes: Int = -1) = AppNotificationIconConfig(
            smallIconCompat = smallIconRes.toIcon(),
            largeIconCompat = context.toIconOrNUll(largeIconRes)
        )

        fun from(smallIconRes: Bitmap, largeIconBitmap: Bitmap? = null) = AppNotificationIconConfig(
            smallIconCompat = smallIconRes.toIcon(),
            largeIconCompat = largeIconBitmap.toIconOrNull()
        )

        fun from(smallIconRes: Bitmap, largeIconBitmap: Uri? = null) = AppNotificationIconConfig(
            smallIconCompat = smallIconRes.toIcon(),
            largeIconCompat = largeIconBitmap.toIconOrNull()
        )

        fun from(context: Context, smallIconRes: Uri, @DrawableRes largeIconRes: Int = -1) = AppNotificationIconConfig(
            smallIconCompat = smallIconRes.toIcon(),
            largeIconCompat = context.toIconOrNUll(largeIconRes)
        )

        fun from(smallIconRes: Uri, largeIconBitmap: Bitmap? = null) = AppNotificationIconConfig(
            smallIconCompat = smallIconRes.toIcon(),
            largeIconCompat = largeIconBitmap.toIconOrNull()
        )

        fun from(smallIconRes: Uri, largeIconBitmap: Uri? = null) = AppNotificationIconConfig(
            smallIconCompat = smallIconRes.toIcon(),
            largeIconCompat = largeIconBitmap.toIconOrNull()
        )
    }
}
