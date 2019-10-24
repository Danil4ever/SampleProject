package simple.code.base.data

import simple.code.base.utils.PreferencesUtils

abstract class DataLayer {

    lateinit var preferences: PreferencesUtils

    private val listScreensUpdate: HashMap<String, Boolean> = HashMap()

    open fun initPreferences(preferences: PreferencesUtils) {
        this.preferences = preferences
    }

    fun isNeedUpdate(screenName: String): Boolean {
        return listScreensUpdate[screenName] ?: false
    }

    fun isUpdated(screenName: String) {
        listScreensUpdate.remove(screenName)
    }

    fun addToNeedUpdate(screenName: String) {
        listScreensUpdate[screenName] = true
    }

}