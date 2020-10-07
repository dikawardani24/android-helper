package digital.klik.helper.component.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.setText(value: CharSequence?, default: CharSequence) {
    setText(value ?: default)
}

fun EditText.clear() {
    text = null
}

fun EditText.clearError() {
    error = null
}

fun EditText.addAfterTextChangedListener(afterTextChangedListner: (newText: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val newText = s.toString().trim()
            afterTextChangedListner(newText)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}