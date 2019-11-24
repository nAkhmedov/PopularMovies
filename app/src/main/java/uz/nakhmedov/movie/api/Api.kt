package uz.nakhmedov.movie.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import uz.nakhmedov.movie.data.model.MovieResponse

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-19
 * Time: 23:16
 * To change this template use File | Settings | File Templates
 */
interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Long) : Observable<MovieResponse>
}