package digital.klik.helper.view

import android.view.View
import com.google.android.material.snackbar.Snackbar
import digital.klik.helper.view.config.SnackBarConfig
import digital.klik.helper.view.constant.SnackBarDuration
import digital.klik.helper.view.listener.SnackBarListener

fun showSnackBar(
    view: View,
    message: String,
    snackBarDuration: SnackBarDuration,
    snackBarConfig: SnackBarConfig? = null,
    action: SnackBarListener? = null
) {
    val duration = when (snackBarDuration) {
        SnackBarDuration.LONG -> Snackbar.LENGTH_LONG
        SnackBarDuration.SHORT -> Snackbar.LENGTH_SHORT
        SnackBarDuration.INDEFINITE -> Snackbar.LENGTH_INDEFINITE
    }

    val snackBar = Snackbar.make(view, message, duration)

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

fun showShortSnackBar(view: View, message: String, snackBarConfig: SnackBarConfig? = null, action: SnackBarListener? = null) {
    showSnackBar(
        view = view,
        message = message,
        snackBarDuration = SnackBarDuration.SHORT,
        snackBarConfig = snackBarConfig,
        action = action
    )
}

fun showLongSnackBar(view: View, message: String, snackBarConfig: SnackBarConfig? = null, action: SnackBarListener? = null) {
    showSnackBar(
        view = view,
        message = message,
        snackBarDuration = SnackBarDuration.LONG,
        snackBarConfig = snackBarConfig,
        action = action
    )
}

fun showIndefinite(view: View, message: String, snackBarConfig: SnackBarConfig? = null, action: SnackBarListener? = null) {
    showSnackBar(
        view = view,
        message = message,
        snackBarDuration = SnackBarDuration.INDEFINITE,
        snackBarConfig = snackBarConfig,
        action = action
    )
}