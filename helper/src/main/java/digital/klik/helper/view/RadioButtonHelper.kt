package digital.klik.helper.view

import android.app.Activity
import android.widget.RadioButton
import android.widget.RadioGroup

@Suppress("unused")
object RadioButtonHelper {
    fun getSelectedItemOf(radioButtons: List<RadioButton>): String? {
        var selectedValue: String? = null

        for (radioButton in radioButtons) {
            if (radioButton.isChecked) {
                selectedValue = radioButton.text.toString()
            }
        }

        return selectedValue
    }

    fun getSelectedItemOf(activity: Activity, radioGroup: RadioGroup) : String? {
        val selectedId = radioGroup.checkedRadioButtonId
        val selectedRadioButton = activity.findViewById<RadioButton>(selectedId)
        return selectedRadioButton?.text?.toString()
    }

    fun setSelectedValueOf(radioButtons: List<RadioButton>, value: String) {
        for (radioButton in radioButtons) {
            val title = radioButton.text.toString()
            radioButton.isChecked = title == value
        }
    }
}