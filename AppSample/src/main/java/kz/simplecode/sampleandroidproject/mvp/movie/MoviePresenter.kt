package kz.simplecode.sampleandroidproject.mvp.movie

import com.arellomobile.mvp.InjectViewState
import kz.simplecode.sampleandroidproject.BuildConfig
import kz.simplecode.sampleandroidproject.mvp.sample.SampleFragmentPresenter


@InjectViewState
class MoviePresenter : SampleFragmentPresenter<MovieView>() {

    fun loadMovie(movieId: Int) {
        cd.add(
            serverApi.movie(movieId, BuildConfig.API_KEY)
                .doFinally { showLoadingIndicator(false) }
                .subscribe({
                    viewState.showMovie(it)
                }, {
                    showError(it)
                })
        )
    }


}


