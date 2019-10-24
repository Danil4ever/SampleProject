package kz.simplecode.sampleandroidproject.mvp.sample

import kz.simplecode.sampleandroidproject.data.SampleDataLayer
import kz.simplecode.sampleandroidproject.data.network.ServerAPI
import kz.simplecode.sampleandroidproject.data.network.ServerAPIImpl
import simple.code.base.mvp.BaseMvpActivityPresenter
import simple.code.base.mvp.BaseMvpActivityView

abstract class SampleActivityPresenter<T : BaseMvpActivityView> : BaseMvpActivityPresenter<T>() {

    override val data = SampleDataLayer

    protected val serverApi: ServerAPI = ServerAPIImpl


}