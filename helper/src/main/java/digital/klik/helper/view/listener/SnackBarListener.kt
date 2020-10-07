package digital.klik.helper.view.listener

import android.view.View

interface SnackBarListener {
    fun actionTitle(): String
    fun onClick(view: View)
}