package uz.nakhmedov.movie.ui.util.components

import android.content.Context
import android.util.TypedValue
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import kotlin.math.max

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-24
 * Time: 13:58
 * To change this template use File | Settings | File Templates
 */
class GridAutoFitLayoutManager/* Initially set spanCount to 1, will be changed automatically later. */(
    context: Context,
    columnWidth: Int) : GridLayoutManager(context,1) {
    private var mColumnWidth = 0
    private var mColumnWidthChanged = true
    private var mWidthChanged = true
    private var mWidth = 0

    init {
        setColumnWidth(checkedColumnWidth(context, columnWidth))
    }

    private fun checkedColumnWidth(context: Context, columnWidth: Int): Int {
        var columnWidth = columnWidth
        columnWidth = if (columnWidth <= 0) {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                sColumnWidth.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        } else {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, columnWidth.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        }
        return columnWidth
    }

    private fun setColumnWidth(newColumnWidth: Int) {
        if (newColumnWidth > 0 && newColumnWidth != mColumnWidth) {
            mColumnWidth = newColumnWidth
            mColumnWidthChanged = true
        }
    }

    override fun onLayoutChildren(
        recycler: Recycler,
        state: RecyclerView.State
    ) {
        val width = width
        val height = height
        if (width != mWidth) {
            mWidthChanged = true
            mWidth = width
        }
        if (mColumnWidthChanged && mColumnWidth > 0 && width > 0 && height > 0
            || mWidthChanged
        ) {
            val totalSpace: Int = if (orientation == LinearLayoutManager.VERTICAL) {
                width - paddingRight - paddingLeft
            } else {
                height - paddingTop - paddingBottom
            }
            val spanCount = max(1, totalSpace / mColumnWidth)
            setSpanCount(spanCount)
            mColumnWidthChanged = false
            mWidthChanged = false
        }
        super.onLayoutChildren(recycler, state)
    }

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }

    companion object {
        private const val sColumnWidth = 200 // assume cell width of 200dp
    }
}