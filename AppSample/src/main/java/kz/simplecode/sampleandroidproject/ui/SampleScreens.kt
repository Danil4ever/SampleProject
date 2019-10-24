package kz.simplecode.sampleandroidproject.ui

import androidx.fragment.app.Fragment
import kz.simplecode.sampleandroidproject.ui.movies.MoviesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object SampleScreens {

    class MoviesScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MoviesFragment()
        }
    }


}
