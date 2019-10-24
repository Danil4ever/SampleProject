package simple.code.base.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("dd MMMM yyyy")
    private val progressDateFormat = SimpleDateFormat("EEEE, dd MMMM")

    fun getTimeStamp(year: Int, month: Int, day: Int): Long {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return calendar.timeInMillis / 1000
    }

    fun getDateStr(year: Int, month: Int, day: Int): String {
        val c = Calendar.getInstance()
        c.set(year, month, day)
        return dateFormat.format(c.time)
    }

    fun getDateStr(timestamp: Long): String {
        val c = Calendar.getInstance()
        c.timeInMillis = (timestamp * 1000)
        return dateFormat.format(c.time)
    }

    fun getProgressDateStr(timestamp: Long): String {
        val c = Calendar.getInstance()
        c.timeInMillis = (timestamp * 1000)
        return progressDateFormat.format(c.time)
    }
}