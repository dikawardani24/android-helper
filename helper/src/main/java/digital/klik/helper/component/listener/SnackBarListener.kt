package digital.klik.helper.component.listener

import android.view.View

interface SnackBarListener {
    fun actionTitle(): String
    fun onClick(view: View)
}