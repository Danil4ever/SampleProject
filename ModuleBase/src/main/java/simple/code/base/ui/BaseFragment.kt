package simple.code.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import simple.code.base.mvp.BaseMvpActivityPresenter


abstract class BaseFragment<PRESENTER : BaseMvpActivityPresenter<*>> : androidx.fragment.app.Fragment() {

    protected abstract fun getLayoutResourceId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    lateinit var presenter: PRESENTER

}