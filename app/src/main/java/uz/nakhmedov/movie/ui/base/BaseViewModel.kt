package uz.nakhmedov.movie.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-23
 * Time: 21:58
 * To change this template use File | Settings | File Templates
 */
abstract class BaseViewModel : ViewModel() {

    var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
    }
}