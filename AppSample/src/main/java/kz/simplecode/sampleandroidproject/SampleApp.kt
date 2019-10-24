package kz.simplecode.sampleandroidproject

import kz.simplecode.sampleandroidproject.data.SampleDataLayer
import kz.simplecode.sampleandroidproject.data.database.SampleDatabase
import simple.code.base.BaseApplication


class SampleApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        database = SampleDatabase.getInstance(this)!!
        SampleDataLayer.initPreferences(prefs)

    }

    companion object {
        private lateinit var database: SampleDatabase

        fun getDatabase(): SampleDatabase {
            return database
        }
    }
}