package dika.helper.android.component.extension

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import dika.helper.android.component.exception.ViewException

val TextInputLayout.requiredEditText: EditText
    get() = editText ?: throw ViewException("No EditText has been attached to current TextInputLayout")

var TextInputLayout.text: String
    set(value) = requiredEditText.setText(value)
    get() = requiredEditText.text.toString()

fun TextInputLayout.setText(value: String?, default: String = "") {
    text = value ?: default
}

fun TextInputLayout.clear() {
    requiredEditText.text = null
}


fun TextInputLayout.setErrorInput(error: CharSequence?, isUseEndIcon: Boolean = false) {
    if (!isUseEndIcon) {
        errorIconDrawable = null
    }

    setError(error)
    if (error == null) clearError()
}

fun TextInputLayout.setErrorInput(errorResId: Int, isUseEndIcon: Boolean = false) {
    if (!isUseEndIcon) {
        errorIconDrawable = null
    }

    val message = context.getString(errorResId)
    error = message
}

fun TextInputLayout.clearError() {
    error = null
    isErrorEnabled = false
}

fun TextInputLayout.addAfterTextChangedListener(afterTextChangedListner: (newText: String)-> Unit) {
    requiredEditText.addAfterTextChangedListener(afterTextChangedListner)
}