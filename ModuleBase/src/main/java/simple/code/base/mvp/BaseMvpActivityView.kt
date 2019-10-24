package simple.code.base.mvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView

interface BaseMvpActivityView : MvpView {

    fun selectTab(tabId: Int)

    fun setStatusBarTransparent(transparent: Boolean)

    fun changeStatusBarColor(color: Int)

    fun setStatusBarLightIconsColor(statusBarIconsLight: Boolean)

    fun showBottomNavigation(show: Boolean)

    fun addFragment(fragment: Fragment)

    fun addFragment(fragment: Fragment, addToBackStack: Boolean)

    fun replaceFragment(fragment: Fragment)

    fun clearBackStack()

    fun backPressed()

    fun showError(error: Throwable)

    fun showLoadingIndicator(show: Boolean)

    fun sendFirebaseAnalytics(bundle: Bundle)

    fun finishActivity()

    fun showKeyboard(show: Boolean)
}
