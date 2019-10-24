package simple.code.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.ProvidePresenter
import simple.code.base.androidx.MvpAppCompatFragmentX
import simple.code.base.mvp.BaseMvpFragmentPresenter
import simple.code.base.mvp.BaseMvpFragmentView
import simple.code.base.navigation.BackButtonListener

abstract class BaseMvpFragment<PRESENTER : BaseMvpFragmentPresenter<*>> : MvpAppCompatFragmentX(), BaseMvpFragmentView,
    BackButtonListener {

    abstract var presenter: PRESENTER

    @ProvidePresenter
    fun providePresenter() = presenter

    protected abstract fun getLayoutResourceId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.init(activity!! as BaseActivity<*>)

        if (parentFragment != null && parentFragment is BaseTabContainerFragment) {
            presenter.setCurrentRouter((parentFragment as BaseTabContainerFragment).router)
        }

    }

    protected lateinit var currentView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        currentView = inflater.inflate(getLayoutResourceId(), container, false)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onShow()
    }

    open fun onShow() {
        presenter.checkForNeedUpdate(this::class.java.name)
    }

    override fun onResume() {
        super.onResume()
        onShow()
        presenter.checkForNeedUpdate(this::class.java.name)
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    override fun onBackPressed(): Boolean {
        return false
    }


}