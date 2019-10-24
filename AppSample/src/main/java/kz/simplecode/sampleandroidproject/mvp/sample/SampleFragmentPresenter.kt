package kz.simplecode.sampleandroidproject.mvp.sample

import kz.simplecode.sampleandroidproject.SampleApp
import kz.simplecode.sampleandroidproject.data.SampleDataLayer
import kz.simplecode.sampleandroidproject.data.network.ServerAPI
import kz.simplecode.sampleandroidproject.data.network.ServerAPIImpl
import simple.code.base.mvp.BaseMvpFragmentPresenter
import simple.code.base.mvp.BaseMvpFragmentView


abstract class SampleFragmentPresenter<T : BaseMvpFragmentView> : BaseMvpFragmentPresenter<T>() {

    override val data = SampleDataLayer

    protected val serverApi: ServerAPI = ServerAPIImpl
    protected val database = SampleApp.getDatabase()


}