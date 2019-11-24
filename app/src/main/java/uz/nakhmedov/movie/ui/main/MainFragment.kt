package uz.nakhmedov.movie.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import uz.nakhmedov.movie.R
import uz.nakhmedov.movie.databinding.MainFragmentBinding
import uz.nakhmedov.movie.ui.adapters.MovieAdapter
import uz.nakhmedov.movie.ui.base.BaseFragment
import uz.nakhmedov.movie.ui.util.components.GridAutoFitLayoutManager
import uz.nakhmedov.movie.ui.util.components.GridSpacingItemDecoration
import javax.inject.Inject

class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject lateinit var mViewModel: MainViewModel

    private lateinit var mAdapter : MovieAdapter

    override val layoutId: Int = R.layout.main_fragment

    override val getViewModel: MainViewModel
        get() = mViewModel

    override val toolbar: Toolbar?
        get() = binding().toolbar

    override val bindingVariable: Int = BR.viewModel

    override fun hasMenu(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToLiveData()
    }

    override fun initialize() {
        mAdapter = MovieAdapter(mViewModel)

        context?.let {
            val gridLayoutManager = GridAutoFitLayoutManager(it, 160)

            binding().recyclerView.apply {
                adapter = mAdapter
                layoutManager = gridLayoutManager
                addItemDecoration(GridSpacingItemDecoration(gridLayoutManager.spanCount, 48, true))
            }

            gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (mAdapter.getItemViewType(position)) {
                        R.layout.movie_item -> 1
                        R.layout.loading_item -> gridLayoutManager.spanCount
                        else -> -1
                    }
                }
            }
        }
    }

    private fun subscribeToLiveData() {
        mViewModel.movieData.observe(this, Observer { results ->
            mAdapter.submitList(results)
        })

        mViewModel.messageData.observe(this, Observer { title ->
            Toast.makeText(context, title, Toast.LENGTH_LONG).show()
        })
    }
}
