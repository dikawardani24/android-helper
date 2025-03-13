package dika.helper.android.component.extension

import android.content.Context
import android.widget.Toast
import dika.helper.android.component.constant.ToastDuration

fun Context.showToast(message: String, toastDuration: ToastDuration) {
    Toast.makeText(this, message, toastDuration.value).show()
}

fun Context.showShortToast(message: String) {
    showToast(message, ToastDuration.SHORT)
}

fun Context.showLongToast(message: String) {
    showToast(message, ToastDuration.LONG)
}