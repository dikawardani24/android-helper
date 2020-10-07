package digital.klik.helper.component.extension

import android.view.View

fun View.setVisible(visible: Boolean) {
    val currentVisibility = visibility
    val newVisibility = if (visible) View.VISIBLE else View.GONE

    if (currentVisibility != newVisibility) {
        visibility = newVisibility
    }
}