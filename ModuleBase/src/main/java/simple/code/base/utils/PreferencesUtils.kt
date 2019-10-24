package simple.code.base.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson

class PreferencesUtils(private val context: Context, private val preferencesName: String) {

    private var sharedPref = context.getSharedPreferences(preferencesName, MODE_PRIVATE)
    private var editor = sharedPref.edit()

    fun setString(key: String, value: String, save: Boolean) {
        editor.putString(key, value)
        if (save)
            editor.apply()
    }

    fun saveString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String {
        return sharedPref.getString(key, "")
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPref.getString(key, defaultValue)
    }

    fun setFloat(key: String, value: Float, save: Boolean) {
        editor.putFloat(key, value)
        if (save)
            editor.apply()
    }

    fun saveFloat(key: String, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getFloat(key: String): Float {
        return sharedPref.getFloat(key, -1f)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPref.getFloat(key, defaultValue)
    }

    fun saveLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    fun setLong(key: String, value: Long, save: Boolean) {
        editor.putLong(key, value)
        if (save)
            editor.apply()
    }

    fun getLong(key: String): Long {
        return sharedPref.getLong(key, -1)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return sharedPref.getLong(key, defaultValue)
    }

    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun setInt(key: String, value: Int, save: Boolean) {
        editor.putInt(key, value)
        if (save)
            editor.apply()
    }

    fun getInt(key: String): Int {
        return sharedPref.getInt(key, -1)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPref.getInt(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun setBoolean(key: String, value: Boolean, save: Boolean) {
        editor.putBoolean(key, value)
        if (save)
            editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getBoolean(key: String, value: Boolean): Boolean {
        return sharedPref.getBoolean(key, value)
    }

    fun getAllPrefs(): Map<String, *> {
        return sharedPref.all
    }

    fun clearPreferences() {
        editor.clear()
        editor.apply()

        sharedPref = context.getSharedPreferences(preferencesName, MODE_PRIVATE)
        editor = sharedPref.edit()
    }


    fun <T> setObject(key: String, value: T, save: Boolean) {
        editor.putString(key, Gson().toJson(value))
        if (save)
            editor.apply()
    }

    fun <T> saveObject(key: String, value: T) {
        editor.putString(key, Gson().toJson(value))
        editor.apply()
    }

    fun <T> getObject(key: String, objectClass: Class<T>): T? {
        val json = sharedPref.getString(key, "")
        return Gson().fromJson<T>(json, objectClass)
    }

}
