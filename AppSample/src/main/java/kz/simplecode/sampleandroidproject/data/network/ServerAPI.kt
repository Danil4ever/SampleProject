package kz.simplecode.sampleandroidproject.data.network

import io.reactivex.Observable
import kz.simplecode.sampleandroidproject.data.models.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerAPI {

    @GET("list/{list_id}")
    fun listMovies(
        @Path("list_id") list_id: Int? = 1,
        @Query("page") page: Int? = 1,
        @Query("sort_by") sort_by: String? = "vote_average.asc"
    ): Observable<Movies>




}