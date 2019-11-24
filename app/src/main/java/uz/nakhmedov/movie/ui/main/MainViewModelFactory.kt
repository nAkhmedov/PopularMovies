package uz.nakhmedov.movie.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.nakhmedov.movie.api.Api
import uz.nakhmedov.movie.ui.util.rx.SchedulerProvider
import javax.inject.Inject

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-22
 * Time: 23:23
 * To change this template use File | Settings | File Templates
 */
class MainViewModelFactory @Inject constructor(
    private val api: Api,
    private val schedulerProvider: SchedulerProvider) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(api, schedulerProvider) as T
    }
}