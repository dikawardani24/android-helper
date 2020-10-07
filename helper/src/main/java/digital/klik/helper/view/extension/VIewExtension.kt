package digital.klik.helper.view.extension

import android.view.View

fun View.setVisible(visible: Boolean) {
    val currentVisibility = visibility
    val newVisibility = if (visible) View.VISIBLE else View.GONE

    if (currentVisibility != newVisibility) {
        visibility = newVisibility
    }
}