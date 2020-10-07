package digital.klik.helper.component.extension

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import digital.klik.helper.component.constant.ToastDuration

fun showToast(context: Context, message: String, toastDuration: ToastDuration) {
    val duration = when(toastDuration) {
        ToastDuration.SHORT -> Toast.LENGTH_SHORT
        ToastDuration.LONG -> Toast.LENGTH_LONG
    }

    Toast.makeText(context, message, duration).show()
}

fun Activity.showToast(message: String, toastDuration: ToastDuration) {
    showToast(this, message, toastDuration)
}

fun Activity.showShortToast(message: String) {
    showToast(message, ToastDuration.SHORT)
}

fun Activity.showLongToast(message: String) {
    showToast(message, ToastDuration.LONG)
}

fun Fragment.showToast(message: String, toastDuration: ToastDuration) {
    showToast(
        requireContext(),
        message,
        toastDuration
    )
}

fun Fragment.showShortToast(message: String) {
    showToast(message,ToastDuration.SHORT)
}

fun Fragment.showLongToast(message: String) {
    showToast(message, ToastDuration.LONG)
}