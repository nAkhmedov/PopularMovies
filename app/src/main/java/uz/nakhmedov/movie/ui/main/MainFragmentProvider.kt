package uz.nakhmedov.movie.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-23
 * Time: 00:21
 * To change this template use File | Settings | File Templates
 */
@Module
abstract class MainFragmentProvider {
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun provideMainFragmentFactory(): MainFragment
}