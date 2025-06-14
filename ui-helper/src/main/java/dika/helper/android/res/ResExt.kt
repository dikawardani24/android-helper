package dika.helper.android.res

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.IconCompat

fun Context.toIcon(@DrawableRes res: Int): IconCompat = IconCompat.createWithResource(this, res)

fun Context.toIconOrNUll(@DrawableRes res: Int?): IconCompat? {
    res ?: return null
    return toIcon(res)
}

fun Bitmap.toIcon(): IconCompat = IconCompat.createWithBitmap(this)

fun Bitmap?.toIconOrNull(): IconCompat? {
    this ?: return null
    return toIcon()
}

fun Uri.toIcon(): IconCompat = IconCompat.createWithContentUri(this)

fun Uri?.toIconOrNull(): IconCompat? {
    this ?: return null
    return toIcon()
}

fun Context.getColorFromRes(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)