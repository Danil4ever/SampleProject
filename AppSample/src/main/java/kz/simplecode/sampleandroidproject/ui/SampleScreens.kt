package kz.simplecode.sampleandroidproject.ui

import androidx.fragment.app.Fragment
import kz.simplecode.sampleandroidproject.ui.movie.MovieFragment
import kz.simplecode.sampleandroidproject.ui.movies.MoviesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object SampleScreens {

    class MoviesScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MoviesFragment()
        }
    }

    class MovieScreen(private val movieId: Int) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MovieFragment.newInstance(movieId)
        }
    }

}
