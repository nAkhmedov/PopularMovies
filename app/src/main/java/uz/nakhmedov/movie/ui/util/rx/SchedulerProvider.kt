package uz.nakhmedov.movie.ui.util.rx

import io.reactivex.Scheduler

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-20
 * Time: 00:28
 * To change this template use File | Settings | File Templates
 */
interface SchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}