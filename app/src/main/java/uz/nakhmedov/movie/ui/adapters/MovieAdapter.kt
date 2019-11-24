package uz.nakhmedov.movie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import uz.nakhmedov.movie.R
import uz.nakhmedov.movie.data.model.Movie
import uz.nakhmedov.movie.ui.main.MainViewModel

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-24
 * Time: 12:35
 * To change this template use File | Settings | File Templates
 */
class MovieAdapter(
    private val mViewModel: MainViewModel) : PagedListAdapter<Movie, BaseViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            R.layout.movie_item -> BaseViewHolder.create(LayoutInflater.from(parent.context), parent, R.layout.movie_item)
            R.layout.loading_item -> BaseViewHolder.create(LayoutInflater.from(parent.context), parent, R.layout.loading_item)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.movie_item -> holder.bindTo(getItem(position), mViewModel)
        }
    }

    private fun hasExtraRow(): Boolean {
        return mViewModel.dataLoading.get()
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position >= itemCount - 1) {
            R.layout.loading_item
        } else {
            R.layout.movie_item
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
