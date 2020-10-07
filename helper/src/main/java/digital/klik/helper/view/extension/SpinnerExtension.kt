package digital.klik.helper.view.extension

import android.widget.ArrayAdapter
import android.widget.Spinner
import digital.klik.helper.common.InstanceHelper

fun Spinner.getSelectedItemInString(): String {
    return selectedItem.toString()
}

fun Spinner.setSelectedItem(selectedValue: String) {
    val spinnerAdapter = adapter
    if (spinnerAdapter is ArrayAdapter<*>) {
        InstanceHelper.tryCast<ArrayAdapter<String>>(spinnerAdapter) {
            val position = getPosition(selectedValue)
            setSelection(position)
        }
    }
}