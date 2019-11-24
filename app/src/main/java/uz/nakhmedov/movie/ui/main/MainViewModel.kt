package uz.nakhmedov.movie.ui.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import timber.log.Timber
import uz.nakhmedov.movie.api.Api
import uz.nakhmedov.movie.data.MovieDataSourceFactory
import uz.nakhmedov.movie.data.model.Movie
import uz.nakhmedov.movie.ui.base.BaseViewModel
import uz.nakhmedov.movie.ui.util.rx.SchedulerProvider

class MainViewModel(
    api: Api,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    var movieData: LiveData<PagedList<Movie>>
    val messageData = MutableLiveData<String>()
    val dataLoading = ObservableBoolean(false)
    val isConnectedToInternet = ObservableBoolean(false)
    private val sourceFactory: MovieDataSourceFactory


    init {
        observeConnection()

        sourceFactory = MovieDataSourceFactory(api, schedulerProvider, mCompositeDisposable, dataLoading)
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(true)
            .build()

        movieData = LivePagedListBuilder(sourceFactory, config).build()
    }

    fun onItemClick(item: Movie) {
        messageData.value = item.title
    }

    private fun observeConnection() {
        val disposable = ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(schedulerProvider.io())
            .subscribe ({ hasInternet ->
                isConnectedToInternet.set(hasInternet)
                if (hasInternet) {
                    sourceFactory.refresh()
                }
            }, Timber::e)

        mCompositeDisposable.add(disposable)
    }
}
