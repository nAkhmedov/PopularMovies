package uz.nakhmedov.movie.ui.main

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-22
 * Time: 23:22
 * To change this template use File | Settings | File Templates
 */
@Module
class MainFragmentModule {
    @Provides
    fun provideMainViewModel(factory: MainViewModelFactory, fragment: MainFragment): MainViewModel {
        return ViewModelProviders.of(fragment, factory).get(MainViewModel::class.java)
    }
}