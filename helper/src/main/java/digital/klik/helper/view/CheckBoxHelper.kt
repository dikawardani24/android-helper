package digital.klik.helper.view

import android.widget.CheckBox

object CheckBoxHelper {

    fun setSelectedItemsOf(checkBoxes: Array<CheckBox>, valuesInString: String?) {
        if (valuesInString == null) return

        for (checkBox in checkBoxes) {
            if (valuesInString.contains(checkBox.text)) {
                checkBox.isChecked = true
            }
        }
    }

    fun setSelectedItemsOf(checkBoxes: List<CheckBox>, valuesInString: String?) {
        setSelectedItemsOf(
            checkBoxes.toTypedArray(),
            valuesInString
        )
    }

    fun getSelectedItemsOf(checkBoxes: Array<CheckBox>): List<String> {
        val selectedItems = ArrayList<String>()

        for (checkBox in checkBoxes) {
            if (checkBox.isChecked) {
                selectedItems.add(checkBox.text.toString())
            }
        }

        return selectedItems
    }

    fun getSelectedItemsInStringOf(checkBoxes: Array<CheckBox>): String {
        val selectedItems = getSelectedItemsOf(checkBoxes)
        return selectedItems.joinToString(", ")
    }

    fun getSelectedItemsInStringOf(checkBoxes: List<CheckBox>) : String {
        return getSelectedItemsInStringOf(checkBoxes.toTypedArray())
    }
}
