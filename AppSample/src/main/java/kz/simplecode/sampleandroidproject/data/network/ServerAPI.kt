package kz.simplecode.sampleandroidproject.data.network

import io.reactivex.Observable
import kz.simplecode.sampleandroidproject.data.models.MovieDetails
import kz.simplecode.sampleandroidproject.data.models.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerAPI {

    @GET("discover/movie")
    fun listMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int? = null,
        @Query("language") language: String? = "ru-Ru",
        @Query("sort_by") sortBy: String? = "popularity.desc"
    ): Observable<Movies>


    @GET("movie/{movie_id}")
    fun movie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = "ru-Ru"
    ): Observable<MovieDetails>


}