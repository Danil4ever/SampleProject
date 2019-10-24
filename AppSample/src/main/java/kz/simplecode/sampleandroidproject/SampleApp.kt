package kz.simplecode.sampleandroidproject

import kz.simplecode.sampleandroidproject.data.SampleDataLayer
import simple.code.base.BaseApplication

class SampleApp : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        SampleDataLayer.initPreferences(prefs)

    }
}