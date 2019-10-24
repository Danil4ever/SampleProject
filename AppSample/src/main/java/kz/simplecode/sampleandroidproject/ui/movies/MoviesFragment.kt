package kz.simplecode.sampleandroidproject.ui.movies

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_movies.*
import kz.simplecode.sampleandroidproject.R
import kz.simplecode.sampleandroidproject.data.models.MovieShort
import kz.simplecode.sampleandroidproject.mvp.movies.MoviesPresenter
import kz.simplecode.sampleandroidproject.mvp.movies.MoviesView
import simple.code.base.ui.BaseMvpFragment

class MoviesFragment : BaseMvpFragment<MoviesPresenter>(), MoviesView {

    private lateinit var adapter: MoviesAdapter

    @InjectPresenter
    override lateinit var presenter: MoviesPresenter

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_movies
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter(presenter)
        rvFriends.adapter = adapter

        presenter.loadMovies()

    }

    override fun onShow() {
        super.onShow()
        presenter.showBottomNavigation(true)
    }

    override fun showMovies(list: List<MovieShort>) {
        rvFriends.visibility = View.VISIBLE
        llEmpty.visibility = View.GONE
        adapter.setData(list)
    }

    override fun showEmpty(string: String) {
        tvYear.text = string
        rvFriends.visibility = View.GONE
        llEmpty.visibility = View.VISIBLE
    }
}
