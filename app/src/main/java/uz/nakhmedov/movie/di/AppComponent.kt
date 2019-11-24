package uz.nakhmedov.movie.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import uz.nakhmedov.movie.MovieApp
import uz.nakhmedov.movie.api.ApiModule
import javax.inject.Singleton

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-20
 * Time: 00:32
 * To change this template use File | Settings | File Templates
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ApiModule::class,
    ActivityBuilder::class])
interface AppComponent : AndroidInjector<MovieApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MovieApp>()
}