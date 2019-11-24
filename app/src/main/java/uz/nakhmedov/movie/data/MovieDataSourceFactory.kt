package uz.nakhmedov.movie.data

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import uz.nakhmedov.movie.api.Api
import uz.nakhmedov.movie.data.model.Movie
import uz.nakhmedov.movie.ui.util.rx.SchedulerProvider

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-24
 * Time: 17:15
 * To change this template use File | Settings | File Templates
 */
class MovieDataSourceFactory(
    private val api: Api,
    private val schedulerProvider: SchedulerProvider,
    private val composeDisposable: CompositeDisposable,
    private val dataLoading: ObservableBoolean
) : DataSource.Factory<Long, Movie>() {

    private lateinit var movieDataSource: MovieDataSource
    private val movieDataSourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Long, Movie> {
        movieDataSource = MovieDataSource(api, schedulerProvider, composeDisposable, dataLoading)
        movieDataSourceLiveData.postValue(movieDataSource)
        return movieDataSource
    }

    fun refresh() {
        movieDataSource.invalidate()
    }
}