package simple.code.base.androidx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arellomobile.mvp.MvpDelegate


/**
 * Date: 17.12.2015
 * Time: 14:34
 *
 * @author Yuri Shmakov
 * @author Alexander Bliniov
 * @author Konstantin Tckhovrebov
 */
open class MvpAppCompatActivityX : AppCompatActivity() {
    private var mMvpDelegate: MvpDelegate<out MvpAppCompatActivityX>? = null

    /**
     * @return The [MvpDelegate] being used by this Activity.
     */
    val mvpDelegate: MvpDelegate<*>
        get() {
            if (mMvpDelegate == null) {
                mMvpDelegate = MvpDelegate(this)
            }
            return mMvpDelegate as MvpDelegate<out MvpAppCompatActivityX>
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mvpDelegate.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        mvpDelegate.onAttach()
    }

    override fun onResume() {
        super.onResume()

        mvpDelegate.onAttach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        mvpDelegate.onSaveInstanceState(outState)
        mvpDelegate.onDetach()
    }

    override fun onStop() {
        super.onStop()

        mvpDelegate.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()

        mvpDelegate.onDestroyView()

        if (isFinishing()) {
            mvpDelegate.onDestroy()
        }
    }
}
