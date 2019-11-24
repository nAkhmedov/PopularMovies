package uz.nakhmedov.movie.di

import android.content.Context
import dagger.Module
import dagger.Provides
import uz.nakhmedov.movie.MovieApp
import uz.nakhmedov.movie.ui.util.rx.AppSchedulerProvider
import uz.nakhmedov.movie.ui.util.rx.SchedulerProvider
import javax.inject.Singleton

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-20
 * Time: 00:26
 * To change this template use File | Settings | File Templates
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: MovieApp): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}