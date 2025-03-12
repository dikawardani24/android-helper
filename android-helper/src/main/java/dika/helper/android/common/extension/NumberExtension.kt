package dika.helper.android.common.extension

import dika.helper.android.common.NumberHelper
import kotlin.math.pow

fun Number.format(): String {
    return NumberHelper.format(this)
}

fun Number.format(pattern: String): String {
    return NumberHelper.format(this, pattern)
}

fun Number.format(pattern: String, groupingSeparator: Char, decimalSeparator: Char): String {
    return NumberHelper.format(this, pattern = pattern, groupingSeparator = groupingSeparator, decimalSeparator = decimalSeparator)
}

fun Number.formatToCurrency(pattern: String, currencySymbol: String, groupingSeparator: Char, decimalSeparator: Char): String {
    return NumberHelper.formatToCurrency(
        this,
        pattern,
        currencySymbol,
        groupingSeparator,
        decimalSeparator
    )
}

fun Number.formatToCurrency(pattern: String, currencySymbol: String): String {
    return NumberHelper.formatToCurrency(
        value = this,
        pattern = pattern,
        currencySymbol = currencySymbol
    )
}

fun Number.formatToIndonesiaCurrency(): String {
    return NumberHelper.formatToIndonesiaCurrency(this)
}

fun Number.pow(n : Int): Long {
    return toDouble().pow(n.toDouble()).toLong()
}