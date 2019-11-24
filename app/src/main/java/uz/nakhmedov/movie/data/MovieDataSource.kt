package uz.nakhmedov.movie.data

import androidx.databinding.ObservableBoolean
import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import uz.nakhmedov.movie.api.Api
import uz.nakhmedov.movie.data.model.Movie
import uz.nakhmedov.movie.ui.util.AppConstants
import uz.nakhmedov.movie.ui.util.rx.SchedulerProvider

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-24
 * Time: 16:54
 * To change this template use File | Settings | File Templates
 */
class MovieDataSource(
    private val api: Api,
    private val schedulerProvider: SchedulerProvider,
    private val composeDisposable: CompositeDisposable,
    private val dataLoading: ObservableBoolean
) : PageKeyedDataSource<Long, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Movie>
    ) {
        val disposable = api.getPopularMovies(AppConstants.API_KEY, 1)
            .subscribe({ results ->
                callback.onResult(results.results, null, 2L)
            }, Timber::e)
        composeDisposable.add(disposable)
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        dataLoading.set(true)
        val disposable = api.getPopularMovies(AppConstants.API_KEY, params.key)
            .subscribe({ results ->
                val nextKey = params.key + 1
                callback.onResult(results.results, nextKey)
                dataLoading.set(false)
            }, Timber::e)
        composeDisposable.add(disposable)
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {

    }
}