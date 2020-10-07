package digital.klik.helper.dialog

import android.app.DatePickerDialog
import android.content.Context
import digital.klik.helper.dialog.listener.OnCalendarSelectedListener
import java.util.*

object DatePickerDialogHelper {

    @Suppress("MemberVisibilityCanBePrivate")
    fun showDatePickerDialog(
        context: Context,
        defaultSelection: Calendar = Calendar.getInstance(),
        onCalendarSelectedListener: OnCalendarSelectedListener? = null
    ) {
        val listener: DatePickerDialog.OnDateSetListener? =
            if (onCalendarSelectedListener != null) {
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(Calendar.YEAR, year)
                    selectedCalendar.set(Calendar.MONTH, month)
                    selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    onCalendarSelectedListener.onCalendarSelected(selectedCalendar)
                }
            } else {
                null
            }

        DatePickerDialog(
            context,
            listener,
            defaultSelection.get(Calendar.YEAR),
            defaultSelection.get(Calendar.MONTH),
            defaultSelection.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun showDatePickerDialog(
        context: Context,
        defaultSelection: Calendar = Calendar.getInstance(),
        onCalendarSelectedListener: (selectedCalendar: Calendar) -> Unit
    ) {
        showDatePickerDialog(
            context = context,
            defaultSelection = defaultSelection,
            onCalendarSelectedListener = object : OnCalendarSelectedListener {
                override fun onCalendarSelected(selectedCalendar: Calendar) {
                    onCalendarSelectedListener(selectedCalendar)
                }
            }
        )
    }

}