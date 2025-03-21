package dika.helper.android.component.extension

import android.view.View

fun View.setVisible(visible: Boolean) {
    val currentVisibility = visibility
    val newVisibility = if (visible) View.VISIBLE else View.GONE

    if (currentVisibility != newVisibility) {
        visibility = newVisibility
    }
}

fun View.setHide(hide: Boolean) {
    val currentVisibility = visibility
    val newVisibility = if (hide) View.INVISIBLE else View.VISIBLE

    if (currentVisibility != newVisibility) {
        visibility = newVisibility
    }
}