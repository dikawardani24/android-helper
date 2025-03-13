package dika.helper.data.extension

import dika.helper.data.DateHelper
import java.util.Calendar
import java.util.Date
import java.util.Locale

val Calendar.year get() = get(Calendar.YEAR)
val Calendar.month get() = get(Calendar.MONTH)
val Calendar.dayOfMonth get() = get(Calendar.DAY_OF_MONTH)

fun Date.format(pattern: String, locale: Locale): String {
    return DateHelper.format(this, pattern, locale)
}

fun Date.format(pattern: String): String {
    return DateHelper.format(this, pattern)
}

fun Date.format(): String {
    return DateHelper.format(this)
}

fun Date.formatOrEmpty(pattern: String, locale: Locale): String {
    return DateHelper.formatOrEmpty(
        this,
        pattern,
        locale
    )
}

fun Date.formatOrEmpty(pattern: String): String {
    return DateHelper.formatOrEmpty(this, pattern)
}

fun Date.formatOrEmpty(): String {
    return DateHelper.formatOrEmpty(this)
}

fun Date.formatOrNull(pattern: String, locale: Locale): String? {
    return DateHelper.formatOrNull(
        this,
        pattern,
        locale
    )
}

fun Date.formatOrNull(pattern: String): String? {
    return DateHelper.formatOrNull(this, pattern)
}

fun Date.formatOrNull(): String? {
    return DateHelper.formatOrNull(this)
}

fun Date.formatOrDefault(pattern: String, locale: Locale, default: String): String {
    return DateHelper.formatOrDefault(
        this,
        pattern,
        locale,
        default
    )
}

fun Date.formatOrDefault(pattern: String, default: String): String {
    return DateHelper.formatOrDefault(
        this,
        pattern,
        default
    )
}

fun Date.formatOrDefault(default: String): String {
    return DateHelper.formatOrDefault(this, default)
}

fun String.parse(pattern: String, locale: Locale): Date? {
    return DateHelper.parse(this, pattern, locale)
}

fun String.parse(pattern: String): Date? {
    return DateHelper.parse(this, pattern)
}

fun String.parse(): Date? {
    return DateHelper.parse(this)
}

fun String.parseOrDefault(pattern: String, locale: Locale, default: Date): Date {
    return DateHelper.parseOrDefault(this, pattern, locale, default)
}

fun String.parseOrDefault(pattern: String, default: Date): Date {
    return DateHelper.parseOrDefault(this, pattern, default)
}

fun String.parseOrDefault(default: Date): Date {
    return DateHelper.parseOrDefault(this, default)
}

fun Date.minDate(howManyDays: Int) {
    val result = DateHelper.minDate(this, howManyDays)
    this.time = result.time
}

fun Date.plusDate(howManyDays: Int) {
    val result = DateHelper.plusDate(this, howManyDays)
    this.time = result.time
}

fun Date.minMonth(howManyMonth: Int) {
    val result = DateHelper.minMonth(this, howManyMonth)
    this.time = result.time
}

fun Date.plusMonth(howManyMonth: Int) {
    val result = DateHelper.plusMonth(this, howManyMonth)
    this.time = result.time
}
