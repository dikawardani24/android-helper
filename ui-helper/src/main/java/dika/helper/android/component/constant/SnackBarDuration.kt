package dika.helper.android.component.constant

import com.google.android.material.snackbar.Snackbar

enum class SnackBarDuration(val value: Int) {
    SHORT(Snackbar.LENGTH_SHORT),
    LONG(Snackbar.LENGTH_LONG),
    INDEFINITE(Snackbar.LENGTH_INDEFINITE)
}