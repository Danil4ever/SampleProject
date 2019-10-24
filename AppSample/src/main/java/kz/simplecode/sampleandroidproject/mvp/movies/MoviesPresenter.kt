package kz.simplecode.sampleandroidproject.mvp.movies

import com.arellomobile.mvp.InjectViewState
import kz.simplecode.sampleandroidproject.mvp.sample.SampleFragmentPresenter


@InjectViewState
class MoviesPresenter : SampleFragmentPresenter<MoviesView>() {

    fun loadMovies() {
        cd.add(
            serverApi.listMovies()
                .doOnSubscribe { showLoadingIndicator(true) }
                .doFinally { showLoadingIndicator(false) }
                .subscribe({
                    viewState.showMovies(it.results)
                }, {
                    showError(it)
                })
        )
    }


}


