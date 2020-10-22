package digital.klik.helper.component.extension

import android.widget.ArrayAdapter
import android.widget.Spinner
import digital.klik.helper.common.InstanceHelper

fun Spinner.getSelectedItemInString(): String {
    return selectedItem.toString()
}

fun Spinner.setSelectedItem(selectedValue: String) {
    val spinnerAdapter = adapter
    if (spinnerAdapter is ArrayAdapter<*>) {
        InstanceHelper.cast<ArrayAdapter<String>>(spinnerAdapter) {
            val position = getPosition(selectedValue)
            setSelection(position)
        }
    }
}