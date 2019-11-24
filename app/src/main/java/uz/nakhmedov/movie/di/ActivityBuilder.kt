package uz.nakhmedov.movie.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.nakhmedov.movie.ui.main.MainActivity
import uz.nakhmedov.movie.ui.main.MainFragmentProvider

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-22
 * Time: 23:20
 * To change this template use File | Settings | File Templates
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        MainFragmentProvider::class
    ])
    abstract fun bindMainActivity(): MainActivity

}