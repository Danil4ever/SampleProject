package kz.simplecode.sampleandroidproject.mvp.movies

import kz.simplecode.sampleandroidproject.data.models.MovieShort
import simple.code.base.mvp.BaseMvpFragmentView


interface MoviesView : BaseMvpFragmentView {
    fun showMovies(list: List<MovieShort>)
    fun showEmpty(string: String)
}
