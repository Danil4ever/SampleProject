package simple.code.base.extensions

import android.text.format.DateUtils
import java.util.*

fun Date.isToday(): Boolean = DateUtils.isToday(this.time)

fun Date.isTomorrow(): Boolean = DateUtils.isToday(this.time - DateUtils.DAY_IN_MILLIS)

fun Date.isYesterday(): Boolean = DateUtils.isToday(this.time + DateUtils.DAY_IN_MILLIS)

fun Date.getAge(year: Int, month: Int, day: Int): Int {
    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()

    dob.set(year, month, day)

    var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--
    }

    return age
}

fun Date.getAge(): Int {
    val dob = Calendar.getInstance()
    dob.timeInMillis = this.time * 1000
    val today = Calendar.getInstance()

    var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--
    }

    return age
}


