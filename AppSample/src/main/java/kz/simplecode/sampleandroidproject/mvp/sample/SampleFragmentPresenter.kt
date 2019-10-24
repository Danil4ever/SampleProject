package kz.simplecode.sampleandroidproject.mvp.sample

import kz.simplecode.sampleandroidproject.data.SampleDataLayer
import simple.code.base.mvp.BaseMvpFragmentPresenter
import simple.code.base.mvp.BaseMvpFragmentView


abstract class SampleFragmentPresenter<T : BaseMvpFragmentView> : BaseMvpFragmentPresenter<T>() {

    override val data = SampleDataLayer

    //val serverApi: ServerAPI = ServerAPIImpl


}