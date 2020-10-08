package digital.klik.helper.common.extension

import digital.klik.helper.common.NumberHelper

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