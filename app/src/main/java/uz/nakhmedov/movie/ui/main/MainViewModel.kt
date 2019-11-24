package uz.nakhmedov.movie.ui.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import timber.log.Timber
import uz.nakhmedov.movie.api.Api
import uz.nakhmedov.movie.data.model.Movie
import uz.nakhmedov.movie.ui.base.BaseViewModel
import uz.nakhmedov.movie.ui.util.AppConstants
import uz.nakhmedov.movie.ui.util.rx.SchedulerProvider

class MainViewModel(
    private val api: Api,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    init {
        observeConnection()
    }


    val movieData = MutableLiveData<List<Movie>>()
    val messageData = MutableLiveData<String>()
    val dataLoading = ObservableBoolean(false)
    val isConnectedToInternet = ObservableBoolean(false)

    fun loadMovies() {
        dataLoading.set(true)
        val disposable = api.getPopularMovies(AppConstants.API_KEY)
            .subscribeOn(schedulerProvider.io())
            .doFinally { dataLoading.set(false) }
            .observeOn(schedulerProvider.ui())
            .subscribe ({ response ->
                Timber.i("$response")
                movieData.value = response.results
            }, Timber::e)

        mCompositeDisposable.add(disposable)
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
            }, Timber::e)

        mCompositeDisposable.add(disposable)
    }
}
