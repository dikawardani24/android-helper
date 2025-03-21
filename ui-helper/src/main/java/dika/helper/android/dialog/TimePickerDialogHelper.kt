package dika.helper.android.dialog

import android.app.TimePickerDialog
import android.content.Context
import dika.helper.android.dialog.listener.OnTimeSelectedListener
import dika.helper.core.DateHelper
import dika.helper.core.model.Time

object TimePickerDialogHelper {

    @Suppress("MemberVisibilityCanBePrivate")
    fun showTimePickerDialog(
        context: Context,
        defaultSelection: Time = DateHelper.currentTime(),
        use24HourView: Boolean = false,
        onTimeSelectedListener: OnTimeSelectedListener?
    ) {
        val listener: TimePickerDialog.OnTimeSetListener? =
            if (onTimeSelectedListener != null) {
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    onTimeSelectedListener.onTimeSelected(
                        Time(
                            hourOfDay,
                            minute
                        )
                    )
                }
            } else {
                null
            }

        TimePickerDialog(
            context,
            listener,
            defaultSelection.hour,
            defaultSelection.minute,
            use24HourView
        ).show()
    }

    fun showTimePickerDialog(
        context: Context,
        defaultSelection: Time = DateHelper.currentTime(),
        use24HourView: Boolean = false,
        onTimeSelectedListener: (selectedTime: Time) -> Unit
    ) {
        showTimePickerDialog(
            context = context,
            defaultSelection = defaultSelection,
            use24HourView = use24HourView,
            onTimeSelectedListener = object : OnTimeSelectedListener {
                override fun onTimeSelected(selectedTime: Time) {
                    onTimeSelectedListener(selectedTime)
                }
            }
        )
    }
}