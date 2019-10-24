package kz.simplecode.sampleandroidproject.mvp.movies

import com.arellomobile.mvp.InjectViewState
import kz.simplecode.sampleandroidproject.BuildConfig
import kz.simplecode.sampleandroidproject.data.models.MovieShort
import kz.simplecode.sampleandroidproject.mvp.sample.SampleFragmentPresenter
import kz.simplecode.sampleandroidproject.ui.SampleScreens


@InjectViewState
class MoviesPresenter : SampleFragmentPresenter<MoviesView>() {

    fun loadMovies() {
        cd.add(
            serverApi.listMovies(apiKey = BuildConfig.API_KEY)
                .doOnSubscribe { showLoadingIndicator(true) }
                .doFinally { showLoadingIndicator(false) }
                .subscribe({
                    viewState.showMovies(it.results)
                }, {
                    showError(it)
                })
        )
    }

    fun goToMovieDetails(details: MovieShort) {
        router.navigateTo(SampleScreens.MovieScreen(details.id))
    }


}


