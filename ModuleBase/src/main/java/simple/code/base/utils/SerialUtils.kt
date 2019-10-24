package simple.code.base.utils

import com.google.gson.Gson

object SerialUtils {

    fun <T> objectToStr(value: T): String {
        return Gson().toJson(value)
    }


    fun <T> objectFromStr(data: String, objectClass: Class<T>): T? {
        return Gson().fromJson<T>(data, objectClass)
    }
}