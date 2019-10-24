package simple.code.base.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import simple.code.base.R
import simple.code.base.androidx.MvpAppCompatActivityX
import simple.code.base.mvp.BaseMvpActivityPresenter
import simple.code.base.mvp.BaseMvpActivityView
import simple.code.base.navigation.BackButtonListener
import simple.code.base.utils.SnackbarUtils
import timber.log.Timber


abstract class BaseActivity<PRESENTER : BaseMvpActivityPresenter<*>> : MvpAppCompatActivityX(),
    BaseMvpActivityView {

    protected abstract val presenter: PRESENTER

    @ProvidePresenter
    fun providePresenter() = presenter

    protected abstract fun getLayoutResourceId(): Int


    private var loadingIndicator: FrameLayout? = null
    protected var bottomNavigation: BottomNavigationView? = null

    /** Implement MvpAppCompatActivity **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutResourceId() != -1) setContentView(getLayoutResourceId())

        presenter.init(this)

        loadingIndicator = findViewById(R.id.loadingIndicator)
        bottomNavigation = findViewById(R.id.bottomNavigation)
    }


    private var doubleBackToExitPressedOnce = false
    private val exitHandler = Handler()

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
            }
        }
        if (fragment != null
            && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        }

        //TODO внедрить везде Cicerone и убрать этот код
        if (supportFragmentManager.backStackEntryCount >= 1) {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction().commitNow()
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            this.doubleBackToExitPressedOnce = true

            Toast.makeText(this, getString(R.string.hint_exit), Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            if (fragment.isVisible) fragment.onActivityResult(requestCode, resultCode, data)
        }
    }
    /** END Implement MvpAppCompatActivity **/

    /** Implement BaseMvpFragmentView **/


    override fun changeStatusBarColor(color: Int) {
        window.statusBarColor = color
    }

    override fun selectTab(tabId: Int) {}

    override fun setStatusBarTransparent(transparent: Boolean) {
        if (transparent) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        } else {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE xor View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = resources.getColor(R.color.colorPrimaryDark)
        }
    }


    override fun setStatusBarLightIconsColor(statusBarIconsLight: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (statusBarIconsLight) {
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility =
                    window.decorView.systemUiVisibility xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

    override fun showBottomNavigation(show: Boolean) {
        bottomNavigation?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun addFragment(fragment: Fragment) {
        addFragment(fragment, true)
    }

    override fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(fragment::class.java.name)
        }
        transaction.commit()
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()

    }

    override fun clearBackStack() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

    }

    override fun backPressed() {
        onBackPressed()
    }

    override fun showError(error: Throwable) {
        Timber.e(error)

        val frameLayout: FrameLayout? = findViewById(R.id.container)

        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        if (frameLayout == null) {
            val parentLayout = findViewById<View>(android.R.id.content)
            imm.hideSoftInputFromWindow(parentLayout.windowToken, 0)
            SnackbarUtils.showSnackbar(parentLayout, error.localizedMessage)
        } else {
            imm.hideSoftInputFromWindow(frameLayout.windowToken, 0)
            SnackbarUtils.showSnackbar(frameLayout, error.localizedMessage)
        }


    }

    override fun showLoadingIndicator(show: Boolean) {
        loadingIndicator?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun sendFirebaseAnalytics(bundle: Bundle) {
        //firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    override fun finishActivity() {
        finish()
    }

    override fun showKeyboard(show: Boolean) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (show) {
            imm.showSoftInput(this.currentFocus, InputMethodManager.SHOW_IMPLICIT)
        } else {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }

    }

    /** END Implement BaseMvpFragmentView **/

    val navigator: Navigator = SupportAppNavigator(this as FragmentActivity, R.id.container)

}