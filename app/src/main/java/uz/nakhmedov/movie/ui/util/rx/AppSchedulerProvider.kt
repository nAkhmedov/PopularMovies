package uz.nakhmedov.movie.ui.util.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-20
 * Time: 00:28
 * To change this template use File | Settings | File Templates
 */
class AppSchedulerProvider : SchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
