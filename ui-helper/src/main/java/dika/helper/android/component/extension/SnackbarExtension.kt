package dika.helper.android.component.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar
import dika.helper.android.component.config.SnackBarConfig
import dika.helper.android.component.constant.SnackBarDuration
import dika.helper.android.component.listener.SnackBarListener

fun View.showSnackBar(
    message: String,
    snackBarDuration: SnackBarDuration,
    snackBarConfig: SnackBarConfig? = null,
    action: SnackBarListener? = null
) {
    val snackBar = Snackbar.make(this, message, snackBarDuration.value)

    if (action != null) {
        snackBar.setAction(action.actionTitle()) {
            action.onClick(it)
        }
    }

    if (snackBarConfig != null) {
        snackBar.run {
            setActionTextColor(snackBarConfig.actionTextColorResId)
            setBackgroundTint(snackBarConfig.backgroundTintResId)
        }
    }

    snackBar.show()
}

fun View.showShortSnackBar(message: String, snackBarConfig: SnackBarConfig? = null, action: SnackBarListener? = null) {
    showSnackBar(
        message = message,
        snackBarDuration = SnackBarDuration.SHORT,
        snackBarConfig = snackBarConfig,
        action = action
    )
}

fun View.showLongSnackBar(message: String, snackBarConfig: SnackBarConfig? = null, action: SnackBarListener? = null) {
    showSnackBar(
        message = message,
        snackBarDuration = SnackBarDuration.LONG,
        snackBarConfig = snackBarConfig,
        action = action
    )
}

fun View.showIndefinite(message: String, snackBarConfig: SnackBarConfig? = null, action: SnackBarListener? = null) {
    showSnackBar(
        message = message,
        snackBarDuration = SnackBarDuration.INDEFINITE,
        snackBarConfig = snackBarConfig,
        action = action
    )
}