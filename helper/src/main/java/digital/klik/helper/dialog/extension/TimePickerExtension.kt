package digital.klik.helper.dialog.extension

import android.content.Context
import digital.klik.helper.common.DateHelper
import digital.klik.helper.dialog.TimePickerDialogHelper
import digital.klik.helper.dialog.listener.OnTimeSelectedListener
import digital.klik.helper.common.model.Time

fun Context.showTimePickerDialog(
    defaultSelection: Time = DateHelper.currentTime(),
    use24HourView: Boolean = false,
    onTimeSelectedListener: OnTimeSelectedListener?
) = TimePickerDialogHelper.showTimePickerDialog(this, defaultSelection, use24HourView, onTimeSelectedListener)

fun Context.showTimePickerDialog(
    defaultSelection: Time = DateHelper.currentTime(),
    use24HourView: Boolean = false,
    onTimeSelectedListener: (selectedTime: Time) -> Unit
) = TimePickerDialogHelper.showTimePickerDialog(this, defaultSelection, use24HourView, onTimeSelectedListener)
