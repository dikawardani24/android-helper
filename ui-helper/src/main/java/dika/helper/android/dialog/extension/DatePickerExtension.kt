package dika.helper.android.dialog.extension

import android.content.Context
import androidx.fragment.app.Fragment
import dika.helper.android.dialog.DatePickerDialogHelper
import dika.helper.android.dialog.listener.OnCalendarSelectedListener
import java.util.Calendar

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