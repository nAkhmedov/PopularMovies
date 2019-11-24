package uz.nakhmedov.movie.ui.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import uz.nakhmedov.movie.R
import uz.nakhmedov.movie.di.GlideApp

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 2019-11-24
 * Time: 13:17
 * To change this template use File | Settings | File Templates
 */
class BindingUtils {
    companion object {
        @JvmStatic
        @BindingAdapter("visibleOrGone")
        fun bindVisibleOrGone(view: View, bool: Boolean) {
            view.visibility = if (bool) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("toolbarTitle")
        fun bindToolbarTitle(view: TextView, bool: Boolean) {
            val context = view.context
            view.text = if (bool) context.getString(R.string.movies) else context.getString(R.string.waiting_network)
        }

        @JvmStatic
        @BindingAdapter("imageSrc")
        fun setImageSource(imageView: ImageView, imgPath: String?) {
            val drawable = ContextCompat.getDrawable(
                imageView.context,
                R.drawable.ic_local_movies)
            drawable?.let { DrawableCompat.setTint(it, ContextCompat.getColor(imageView.context, R.color.text_grey)) }
            GlideApp.with(imageView.context)
                .load(AppConstants.API_IMG_PATH + imgPath)
                .apply(RequestOptions.centerCropTransform().placeholder(drawable))
                .into(imageView)
        }
    }
}