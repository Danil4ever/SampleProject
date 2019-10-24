package simple.code.base.mvp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import simple.code.base.BaseApplication
import simple.code.base.data.DataLayer
import timber.log.Timber
import java.util.*

abstract class BaseMvpActivityPresenter<T : BaseMvpActivityView> : MvpPresenter<T>() {

    protected abstract val data: DataLayer
    val router: Router = BaseApplication.getRouter()
    protected var tabRouter: Router? = null

    protected lateinit var context: Context

    protected var cd: CompositeDisposable = CompositeDisposable()

    open fun init(context: Context) {
        this.context = context
    }

    open fun selectTab(tabId: Int) {
        viewState.selectTab(tabId)
    }

    fun changeStatusBarColor(color: Int) {
        viewState.changeStatusBarColor(color)
    }

    open fun setStatusBarTransparent(transparent: Boolean) {
        viewState.setStatusBarTransparent(transparent)
    }

    fun setStatusBarLightIconsColor(statusBarIconsLight: Boolean){
        viewState.setStatusBarLightIconsColor(statusBarIconsLight)
    }

    fun showBottomNavigation(show: Boolean) {
        viewState.showBottomNavigation(show)
    }

    fun addFragment(fragment: androidx.fragment.app.Fragment) {
        viewState.addFragment(fragment)
    }

    fun addFragment(fragment: androidx.fragment.app.Fragment, addToBackStack: Boolean) {
        viewState.addFragment(fragment, addToBackStack)
    }

    open fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        viewState.replaceFragment(fragment)
    }

    open fun clearBackStack() {
        viewState.clearBackStack()
    }

    open fun backPressed() {
        viewState.backPressed()
    }

    open fun showError(error: Throwable) {
        Timber.e(error)
        viewState.showError(error.localizedMessage)
    }

    fun showKeyboard(show: Boolean){
        viewState.showKeyboard(show)
    }

    private var timeStart: Long = 0
    private val animationDuration = 1000 //ms
    open fun showLoadingIndicator(show: Boolean) {
        if (show) {
            timeStart = Calendar.getInstance().timeInMillis
            viewState.showLoadingIndicator(show)
        } else {
            val timeEnd = Calendar.getInstance().timeInMillis
            val diffTime = timeEnd - timeStart
            if (diffTime < animationDuration) {
                Handler().postDelayed({
                    viewState.showLoadingIndicator(show)
                }, animationDuration - diffTime)
            } else {
                viewState.showLoadingIndicator(show)
            }
        }
    }

    open fun sendFirebaseAnalytics(bundle: Bundle) {
        viewState.sendFirebaseAnalytics(bundle)
    }

    open fun finishActivity() {
        viewState.finishActivity()
    }

    open fun onDestroyView() {
        if (cd.isDisposed.not()) {
            cd.dispose()
        }

    }

    fun setCurrentTabFragmentRouter(router: Router) {
        this.tabRouter = router
    }




}
