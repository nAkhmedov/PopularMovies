package uz.nakhmedov.movie.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-23
 * Time: 00:30
 * To change this template use File | Settings | File Templates
 */
abstract class BaseFragment<B : ViewDataBinding, M : ViewModel> : DaggerFragment() {
    private var baseActivity: BaseActivity? = null

    private lateinit var mBinding: B

    abstract val layoutId: Int
    abstract val getViewModel: M
    abstract val bindingVariable: Int

    fun binding(): B {
        return mBinding
    }

    abstract fun hasMenu(): Boolean

    abstract val toolbar: Toolbar?

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity: BaseActivity = context
            this.baseActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(hasMenu())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initToolbar()
        initialize()
        return mBinding.root
    }

    protected abstract fun initialize()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBinding.setVariable(bindingVariable, getViewModel)
        mBinding.executePendingBindings()
    }

    private fun initToolbar() {
        toolbar?.let {
            baseActivity?.setSupportActionBar(toolbar)
        }
    }

    protected fun setActionBarTitle(title: String) {
        baseActivity?.supportActionBar?.title = title
    }

    fun showBackBtn() {
        val actionBar = baseActivity?.supportActionBar
        actionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.elevation = 0.2f
        }
    }
}