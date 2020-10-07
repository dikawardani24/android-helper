package digital.klik.helper.dialog.extension

import android.content.Context
import androidx.fragment.app.Fragment
import digital.klik.helper.dialog.DatePickerDialogHelper
import digital.klik.helper.dialog.listener.OnCalendarSelectedListener
import java.util.*

fun Context.showDatePickerDialog(
    defaultSelection: Calendar = Calendar.getInstance(),
    onCalendarSelectedListener: OnCalendarSelectedListener? = null
) = DatePickerDialogHelper.showDatePickerDialog(this, defaultSelection, onCalendarSelectedListener)

fun Context.showDatePickerDialog(
    defaultSelection: Calendar = Calendar.getInstance(),
    onCalendarSelectedListener: (selectedCalendar: Calendar) -> Unit
) = DatePickerDialogHelper.showDatePickerDialog(this, defaultSelection, onCalendarSelectedListener)

fun Fragment.showDatePickerDialog(
    defaultSelection: Calendar = Calendar.getInstance(),
    onCalendarSelectedListener: OnCalendarSelectedListener? = null
) = DatePickerDialogHelper.showDatePickerDialog(requireActivity(), defaultSelection, onCalendarSelectedListener)

fun Fragment.showDatePickerDialog(
    defaultSelection: Calendar = Calendar.getInstance(),
    onCalendarSelectedListener: (selectedCalendar: Calendar) -> Unit
) = DatePickerDialogHelper.showDatePickerDialog(requireActivity(), defaultSelection, onCalendarSelectedListener)