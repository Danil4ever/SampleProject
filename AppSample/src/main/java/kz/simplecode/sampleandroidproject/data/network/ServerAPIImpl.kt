package kz.simplecode.sampleandroidproject.data.network

import io.reactivex.Observable
import kz.simplecode.sampleandroidproject.BuildConfig
import kz.simplecode.sampleandroidproject.data.models.MovieDetails
import kz.simplecode.sampleandroidproject.data.models.Movies
import simple.code.base.network.RetrofitServiceGenerator

object ServerAPIImpl : ServerAPI {
    private var api =
        RetrofitServiceGenerator.createService(ServerAPI::class.java, BuildConfig.SERVER_URL)

    override fun listMovies(
        apiKey: String,
        page: Int?,
        language: String?,
        sortBy: String?
    ): Observable<Movies> {
        return api.listMovies(apiKey, page, language, sortBy)
    }

    override fun movie(movieId: Int, apiKey: String, language: String?): Observable<MovieDetails> {
        return api.movie(movieId, apiKey, language)
    }
}