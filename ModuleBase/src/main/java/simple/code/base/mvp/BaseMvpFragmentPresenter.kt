package simple.code.base.mvp

import android.content.Context
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import simple.code.base.BaseApplication
import simple.code.base.data.DataLayer
import simple.code.base.ui.BaseActivity

abstract class BaseMvpFragmentPresenter<T : BaseMvpFragmentView> : MvpPresenter<T>() {

    protected var router: Router = BaseApplication.getRouter()

    protected lateinit var context: Context
    protected var activityPresenter: BaseMvpActivityPresenter<*>? = null

    abstract val data: DataLayer

    var cd: CompositeDisposable = CompositeDisposable()

    open fun init(activity: BaseActivity<*>) {
        activityPresenter = activity.presenter
        this.context = activity
    }

    fun setStatusBarTransparent(transparent: Boolean) {
        activityPresenter?.setStatusBarTransparent(transparent)
    }

    fun setStatusBarLightIconsColor(statusBarIconsLight: Boolean) {
        activityPresenter?.setStatusBarLightIconsColor(statusBarIconsLight)
    }

    fun showBottomNavigation(show: Boolean) {
        activityPresenter?.showBottomNavigation(show)
    }

    fun changeStatusBarColor(color: Int) {
        activityPresenter?.changeStatusBarColor(color)
    }

    fun showError(error: Throwable) {
        activityPresenter?.showError(error)
    }

    open fun showLoadingIndicator(show: Boolean) {
        activityPresenter?.showLoadingIndicator(show)
    }

    fun onDestroyView() {
        if (cd.isDisposed.not()) {
            cd.dispose()
        }
    }

    fun backPressed() {
        activityPresenter?.backPressed()
    }

    fun finishActivity() {
        activityPresenter?.finishActivity()
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
        activityPresenter?.showKeyboard(show)
    }

    fun navigateToScreen(screen: SupportAppScreen) {
        router.navigateTo(screen)
    }


}
