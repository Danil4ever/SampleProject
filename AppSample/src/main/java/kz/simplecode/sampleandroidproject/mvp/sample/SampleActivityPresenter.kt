package kz.simplecode.sampleandroidproject.mvp.sample

import kz.simplecode.sampleandroidproject.data.SampleDataLayer
import simple.code.base.mvp.BaseMvpActivityPresenter
import simple.code.base.mvp.BaseMvpActivityView

abstract class SampleActivityPresenter<T : BaseMvpActivityView> : BaseMvpActivityPresenter<T>() {

    override val data = SampleDataLayer

    //val serverApi: ServerAPI = ServerAPIImpl


}