package digital.klik.helper.common

import digital.klik.helper.common.constant.Pattern
import digital.klik.helper.common.exception.DateFormatException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
object DateHelper {

    private fun validatePattern(pattern: String) {
        if (pattern.isEmpty()) {
            throw DateFormatException("Pattern must be not empty")
        }
    }

    fun format(date: Date, pattern: String, locale: Locale): String {
        validatePattern(pattern)
        val simpleDateFormat = SimpleDateFormat(pattern, locale)
        return simpleDateFormat.format(date)
    }

    fun format(date: Date, pattern: String): String {
        return format(date, pattern, Locale.getDefault())
    }

    fun format(date: Date): String {
        return format(date, Pattern.DATE_FULL.value)
    }

    fun formatOrEmpty(date: Date, pattern: String, locale: Locale): String {
        return try {
            format(date, pattern, locale)
        } catch (e: Exception) {
            ""
        }
    }

    fun formatOrEmpty(date: Date, pattern: String): String {
        return formatOrEmpty(date, pattern, Locale.getDefault())
    }

    fun formatOrEmpty(date: Date): String {
        return formatOrEmpty(date, Pattern.DATE_FULL.value)
    }

    fun formatOrNull(date: Date, pattern: String, locale: Locale): String? {
        return try {
            format(date, pattern, locale)
        } catch (e: Exception) {
            return null
        }
    }

    fun formatOrNull(date: Date, pattern: String): String? {
        return formatOrNull(date, pattern, Locale.getDefault())
    }

    fun formatOrNull(date: Date): String? {
        return formatOrNull(date, Pattern.DATE_FULL.value)
    }

    fun formatOrDefault(date: Date, pattern: String, locale: Locale, default: String) : String {
        return try {
            format(date, pattern, locale)
        } catch (e: Exception) {
            default
        }
    }

    fun formatOrDefault(date: Date, pattern: String, default: String): String {
        return formatOrDefault(date, pattern, Locale.getDefault(), default)
    }

    fun formatOrDefault(date: Date, default: String): String {
        return formatOrDefault(date, Pattern.DATE_FULL.value, default)
    }

    fun parse(dateInString: String, pattern: String, locale: Locale): Date? {
        validatePattern(pattern)

        val simpleDateFormat = SimpleDateFormat(pattern, locale)
        return try {
            simpleDateFormat.parse(dateInString)
        } catch (e: Exception) {
            null
        }
    }

    fun parse(dateInString: String, pattern: String): Date? {
        return parse(dateInString, pattern)
    }

    fun parse(dateInString: String): Date? {
        return parse(dateInString, Pattern.DATE_FULL.value)
    }

    fun parseOrDefault(dateInString: String, pattern: String, locale: Locale, default: Date): Date {
        val result = parse(dateInString, pattern, locale)
        return result ?: default
    }

    fun parseOrDefault(dateInString: String, pattern: String, default: Date): Date {
        return parseOrDefault(dateInString, pattern, Locale.getDefault(), default)
    }

    fun parseOrDefault(dateInString: String, default: Date): Date {
        return parseOrDefault(dateInString, Pattern.DATE_FULL.value, default)
    }

    private fun minOrPlusDate(date: Date, howManyDays: Int): Date {
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.add(Calendar.DAY_OF_MONTH, howManyDays)
        return calendar.time
    }

    fun minDate(date: Date, howManyDays: Int): Date {
        return minOrPlusDate(date, -howManyDays)
    }

    fun plusDate(date: Date, howManyDays: Int): Date {
        return minOrPlusDate(date, howManyDays)
    }

    private fun minOrPlusMonth(date: Date, howManyMonths: Int): Date {
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.add(Calendar.MONTH, howManyMonths)
        return calendar.time
    }

    fun minMonth(date: Date, howManyMonths: Int): Date {
        return minOrPlusMonth(date, -howManyMonths)
    }

    fun plusMonth(date: Date, howManyMonths: Int): Date {
        return minOrPlusMonth(date, howManyMonths)
    }

}