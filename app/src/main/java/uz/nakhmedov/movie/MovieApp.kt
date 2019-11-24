package uz.nakhmedov.movie

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import uz.nakhmedov.movie.di.DaggerAppComponent

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-19
 * Time: 23:35
 * To change this template use File | Settings | File Templates
 */
class MovieApp: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
