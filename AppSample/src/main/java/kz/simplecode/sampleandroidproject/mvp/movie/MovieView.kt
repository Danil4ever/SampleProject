package kz.simplecode.sampleandroidproject.mvp.movie

import kz.simplecode.sampleandroidproject.data.models.MovieDetails
import simple.code.base.mvp.BaseMvpFragmentView


interface MovieView : BaseMvpFragmentView {
    fun showMovie(movie: MovieDetails)
}
