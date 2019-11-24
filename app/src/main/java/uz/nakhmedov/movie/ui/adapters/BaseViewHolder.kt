package uz.nakhmedov.movie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-24
 * Time: 12:36
 * To change this template use File | Settings | File Templates
 */
class BaseViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(data: Any?, viewModel: Any) {
        binding.setVariable(BR.item, data)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    companion object {
        @JvmStatic
        fun create(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
            return BaseViewHolder(binding)
        }
    }
}