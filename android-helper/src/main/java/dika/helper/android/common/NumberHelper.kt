package dika.helper.android.common

import dika.helper.android.common.constant.Pattern
import dika.helper.android.common.constant.Symbol.DEFAULT_DECIMAL_SEPARATOR
import dika.helper.android.common.constant.Symbol.DEFAULT_GROUPING_SEPARATOR
import dika.helper.android.common.constant.Symbol.INDONESIA_CURRENCY_SYMBOL
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

@Suppress("unused", "MemberVisibilityCanBePrivate")
object NumberHelper {

    fun format(value: Number, pattern: String, groupingSeparator: Char, decimalSeparator: Char): String {
        val numberFormat = DecimalFormat(pattern).apply {
            decimalFormatSymbols = DecimalFormatSymbols().apply {
                this.groupingSeparator = groupingSeparator
                this.decimalSeparator = decimalSeparator
            }
        }

        return  numberFormat.format(value)
    }

    fun format(value: Number, groupingSeparator: Char, decimalSeparator: Char): String {
        return format(
            pattern = Pattern.NUMBER.value,
            groupingSeparator = groupingSeparator,
            decimalSeparator = decimalSeparator,
            value = value
        )
    }

    fun format(value: Number, pattern: String): String {
        return format(
            value = value,
            pattern = pattern,
            groupingSeparator = DEFAULT_GROUPING_SEPARATOR,
            decimalSeparator = DEFAULT_DECIMAL_SEPARATOR
        )
    }

    fun format(value: Number): String {
        return format(
            value = value,
            pattern = Pattern.NUMBER.value,
            groupingSeparator = DEFAULT_GROUPING_SEPARATOR,
            decimalSeparator = DEFAULT_DECIMAL_SEPARATOR
        )
    }

    fun formatToCurrency(value: Number, pattern: String, currencySymbol: String, groupingSeparator: Char, decimalSeparator: Char) : String {
        return format(
            pattern = "$currencySymbol $pattern",
            groupingSeparator = groupingSeparator,
            decimalSeparator = decimalSeparator,
            value = value
        )
    }

    fun formatToCurrency(value: Number, pattern: String, currencySymbol: String): String {
        return formatToCurrency(
            value = value,
            pattern = pattern,
            currencySymbol = currencySymbol,
            groupingSeparator = DEFAULT_GROUPING_SEPARATOR,
            decimalSeparator = DEFAULT_DECIMAL_SEPARATOR
        )
    }

    fun formatToIndonesiaCurrency(value: Number): String {
        return formatToCurrency(
            value = value,
            pattern = Pattern.NUMBER.value,
            currencySymbol = "Rp"
        )
    }

    fun formatWithNotation(number: Number, pattern: String): String {
        val suffix = arrayOf(' ', 'M', 'B', 'T', 'P', 'E')
        val numValue: Double = number.toDouble()
        val value = floor(log10(numValue)).toInt()
        val base = value / 6

        val formatter = DecimalFormat(pattern)

        return if (value >= 6 && base < suffix.size) {
            val toFormat = numValue / 10.0.pow(base * 6.toDouble())

            formatter.format(toFormat) + suffix[base]
        } else {
            formatter.format(numValue)
        }
    }

    fun formatWithNotation(number: Number): String {
        return formatWithNotation(number, Pattern.NUMBER.value)
    }

    fun formatWithNotation(currencySymbol: String, number: Number): String {
        return "$currencySymbol ${formatWithNotation(number)}"
    }

    fun formatWithNotationAndIndCurrency(value: Number): String {
        return formatWithNotation(INDONESIA_CURRENCY_SYMBOL, value)
    }
}