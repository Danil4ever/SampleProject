package simple.code.base.extensions

import android.util.Patterns
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols


fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPasswordBy6Symbols(): Boolean = this.length > 5

fun String.isValidPasswordByRegister(): Boolean =
    (this).matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#\$%^&*]{6,}".toRegex())


fun String.toHexString(byteArray: ByteArray): String =
    byteArray.map { String.format("%02X", (it.toInt() and 0xFF)) }.joinToString(separator = "")


fun String.formatNumber(): String {
    val df = DecimalFormat()
    df.isGroupingUsed = true
    df.groupingSize = 3

    val symbols = DecimalFormatSymbols()
    symbols.groupingSeparator = ' '

    df.decimalFormatSymbols = symbols

    var str = df.format(this.toFloat())

    str = str.replace(",", ".")

    return str
}

