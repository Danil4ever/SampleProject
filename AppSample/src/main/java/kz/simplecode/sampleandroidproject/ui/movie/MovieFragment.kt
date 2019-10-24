package kz.simplecode.sampleandroidproject.ui.movie

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie.*
import kz.simplecode.sampleandroidproject.R
import kz.simplecode.sampleandroidproject.data.models.MovieDetails
import kz.simplecode.sampleandroidproject.mvp.movie.MoviePresenter
import kz.simplecode.sampleandroidproject.mvp.movie.MovieView
import kz.simplecode.sampleandroidproject.utils.SampleConstants
import simple.code.base.ui.BaseMvpFragment
import java.util.*

class MovieFragment : BaseMvpFragment<MoviePresenter>(), MovieView {

    companion object {
        private const val DATA_MOVIE_ID = "DATA_MOVIE_ID"

        fun newInstance(movieId: Int): MovieFragment {
            val args = Bundle()
            args.putInt(DATA_MOVIE_ID, movieId)
            val fragment = MovieFragment()
            fragment.arguments = args
            return fragment
        }


    }

    @InjectPresenter
    override lateinit var presenter: MoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments!!.containsKey(DATA_MOVIE_ID)) {
            presenter.showLoadingIndicator(true)
            val movieId = arguments!!.getInt(DATA_MOVIE_ID)
            presenter.loadMovie(movieId)
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_movie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivBack.setOnClickListener { presenter.backPressed() }
    }

    override fun showMovie(movie: MovieDetails) {
        tvDialogTitle.text = movie.title

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = movie.releaseDate.time
        val year = calendar.get(Calendar.YEAR)
        tvReleaseYear.text = "${context?.getString(R.string.year)}: $year"

        tvYear.text = "${context?.getString(R.string.budget)}: ${movie.budget}$"

        tvDescription.text = movie.overview

        Picasso.get().load(SampleConstants.IMAGE_URL_W500 + movie.posterPath).into(ivPoster)
    }


}
