package kz.simplecode.sampleandroidproject.data.network

import io.reactivex.Observable
import kz.simplecode.sampleandroidproject.BuildConfig
import kz.simplecode.sampleandroidproject.data.models.Movies
import simple.code.base.network.RetrofitServiceGenerator

object ServerAPIImpl : ServerAPI {
    private var api = RetrofitServiceGenerator.createService(ServerAPI::class.java, BuildConfig.SERVER_URL, BuildConfig.TOKEN)

    override fun listMovies(list_id: Int?, page: Int?, sort_by: String?): Observable<Movies> {
        return api.listMovies(list_id, page, sort_by)
    }
}