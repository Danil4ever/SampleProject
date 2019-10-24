package simple.code.base.extensions

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText


fun EditText.validate(validator: (String) -> Boolean, message: String) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
}

fun EditText.validate(
    validator1: (String) -> Boolean,
    validator2: (String) -> Boolean,
    message1: String,
    message2: String
) {
    this.afterTextChanged {
        this.error = if (validator1(it)) {
            if (validator2(it)) {
                null
            } else {
                message2
            }
        } else {
            message1
        }
    }
    this.error = if (validator1(this.text.toString())) {
        if (validator2(this.text.toString())) {
            null
        } else {
            message2
        }
    } else {
        message1
    }
}


fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })

}

fun Editable.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Editable.isValidPassword(): Boolean =
    (this).matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#\$%^&*]{6,}".toRegex())


fun Editable.toHexString(byteArray: ByteArray): String =
    byteArray.map { String.format("%02X", (it.toInt() and 0xFF)) }.joinToString(separator = "")
