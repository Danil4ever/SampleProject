package kz.simplecode.sampleandroidproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.simplecode.sampleandroidproject.R
import kz.simplecode.sampleandroidproject.mvp.main.MainActivityPresenter
import kz.simplecode.sampleandroidproject.mvp.main.MainActivityView
import simple.code.base.ui.BaseActivity
import simple.code.base.ui.BaseMvpFragment
import simple.code.base.ui.BaseTabContainerFragment

class MainActivity : BaseActivity<MainActivityPresenter>(), MainActivityView {

    @InjectPresenter
    override lateinit var presenter: MainActivityPresenter

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottomNavigation?.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            clearBackStack()
            changeTab(item.itemId)
            return@OnNavigationItemSelectedListener true
        })

    }

    override fun selectTab(tabId: Int) {
        bottomNavigation?.selectedItemId = tabId
    }

    private fun changeTab(tab: Int) {
        presenter.showLoadingIndicator(false)
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        var newFragment = fm.findFragmentByTag(tab.toString())

        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return

        val transaction = fm.beginTransaction()

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        if (newFragment == null) {
            newFragment = when (tab) {
//                R.id.navigation_movies -> ShopTabFragment()
//                R.id.navigation_books -> GamesTabFragment()
                else -> throw IllegalArgumentException()
            }

            transaction.add(R.id.container, newFragment!!, tab.toString())
        } else {
            transaction.show(newFragment)
            if (newFragment is BaseTabContainerFragment) newFragment.onShow()
            if (newFragment is BaseMvpFragment<*>) newFragment.onShow()
        }

        transaction.commitNow()

        if (newFragment is BaseTabContainerFragment)
            presenter.setCurrentTabFragmentRouter(newFragment.router)
    }
}