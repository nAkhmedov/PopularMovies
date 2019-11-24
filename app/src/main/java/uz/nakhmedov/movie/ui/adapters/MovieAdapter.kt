package uz.nakhmedov.movie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
    private val mViewModel: MainViewModel) : RecyclerView.Adapter<BaseViewHolder>() {

    private var mItems: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder.create(LayoutInflater.from(parent.context), parent, R.layout.movie_item)
    }

    override fun getItemCount() = mItems.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindTo(mItems[position], mViewModel)

    }

    fun setData(data: List<Movie>) {
        mItems.addAll(data)
        notifyDataSetChanged()
    }
}