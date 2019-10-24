package simple.code.base.mvp

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import simple.code.base.BaseApplication
import simple.code.base.data.DataLayer
import simple.code.base.ui.BaseActivity

abstract class BaseMvpFragmentPresenter<T : BaseMvpFragmentView> : MvpPresenter<T>() {

    protected var router: Router = BaseApplication.getRouter()

    protected var activity: BaseActivity<*>? = null

    protected abstract val data: DataLayer

    protected var cd: CompositeDisposable = CompositeDisposable()

    open fun init(activity: BaseActivity<*>) {
        this.activity = activity
    }

    fun setStatusBarTransparent(transparent: Boolean) {
        activity?.setStatusBarTransparent(transparent)
    }

    fun setStatusBarLightIconsColor(statusBarIconsLight: Boolean) {
        activity?.setStatusBarLightIconsColor(statusBarIconsLight)
    }

    fun showBottomNavigation(show: Boolean) {
        activity?.showBottomNavigation(show)
    }

    fun changeStatusBarColor(color: Int) {
        activity?.changeStatusBarColor(color)
    }

    fun showError(error: Throwable) {
        activity?.showError(error)
    }

    open fun showLoadingIndicator(show: Boolean) {
        activity?.showLoadingIndicator(show)
    }

    fun onDestroyView() {
        if (cd.isDisposed.not()) {
            cd.dispose()
        }
    }

    fun backPressed() {
        activity?.backPressed()
    }

    fun finishActivity() {
        activity?.finishActivity()
    }

    fun setCurrentRouter(router: Router) {
        this.router = router
    }

    fun checkForNeedUpdate(screenName: String) {
        if (data.isNeedUpdate(screenName)) updateData(screenName)
    }

    open fun updateData(screenName: String) {
        data.isUpdated(screenName)
    }

    fun showKeyboard(show: Boolean) {
        activity?.showKeyboard(show)
    }

    fun navigateToScreen(screen: SupportAppScreen) {
        router.navigateTo(screen)
    }


}
