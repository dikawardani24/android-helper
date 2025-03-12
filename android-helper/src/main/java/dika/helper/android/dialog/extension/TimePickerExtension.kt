package dika.helper.android.dialog.extension

import android.content.Context
import androidx.fragment.app.Fragment
import dika.helper.android.common.DateHelper
import dika.helper.android.common.model.Time
import dika.helper.android.dialog.TimePickerDialogHelper
import dika.helper.android.dialog.listener.OnTimeSelectedListener

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


fun Fragment.showTimePickerDialog(
    defaultSelection: Time = DateHelper.currentTime(),
    use24HourView: Boolean = false,
    onTimeSelectedListener: OnTimeSelectedListener?
) = TimePickerDialogHelper.showTimePickerDialog(requireContext(), defaultSelection, use24HourView, onTimeSelectedListener)

fun Fragment.showTimePickerDialog(
    defaultSelection: Time = DateHelper.currentTime(),
    use24HourView: Boolean = false,
    onTimeSelectedListener: (selectedTime: Time) -> Unit
) = TimePickerDialogHelper.showTimePickerDialog(requireContext(), defaultSelection, use24HourView, onTimeSelectedListener)
