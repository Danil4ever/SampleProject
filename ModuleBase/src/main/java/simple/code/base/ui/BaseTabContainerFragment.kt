package simple.code.base.ui


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import simple.code.base.R
import simple.code.base.navigation.BackButtonListener
import simple.code.base.navigation.LocalCiceroneHolder
import simple.code.base.navigation.RouterProvider

abstract class BaseTabContainerFragment : Fragment(), RouterProvider,
    BackButtonListener {

    private var navigator: Navigator? = null

    protected abstract val mainScreen: Screen

    private var ciceroneHolder = LocalCiceroneHolder

    private val cicerone: Cicerone<Router>
        get() = ciceroneHolder.getCicerone(this::class.java.simpleName)

    private fun getNavigator(): Navigator {
        if (navigator == null) {
            navigator = SupportAppNavigator(activity, childFragmentManager, R.id.ftc_container)
        }
        return navigator!!
    }

    override fun getRouter(): Router {
        return cicerone.router
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onShow()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.ftc_container) == null) {
            cicerone.router.replaceScreen(mainScreen)
        }
    }

    open fun onShow() {
        for (f in childFragmentManager.fragments) {
            if (f.isVisible && f is BaseMvpFragment<*>) {
                f.onShow()
                break
            }
        }
    }

    override fun onResume() {
        cicerone.navigatorHolder.setNavigator(getNavigator())
        super.onResume()
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in childFragmentManager.fragments) {
            if (fragment.isVisible) fragment.onActivityResult(requestCode, resultCode, data)
        }
    }


    override fun onBackPressed(): Boolean {
        val fm = childFragmentManager
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
            && (fragment as BackButtonListener).onBackPressed()) {
            return true
        }

        if (childFragmentManager.backStackEntryCount > 0) {
            router.exit()
            return true
        }

        return false

    }
}
