package com.rokas.barokas.component.imageloader

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rokas.barokas.R
import javax.inject.Inject

class ImageLoader @Inject constructor() {
    fun loadRoundedImage(imageView: ImageView, url: String, onFinishLoading: () -> Unit) {
        Glide
            .with(imageView.context)
            .load(url)
            .listener(getRequestListener(onFinishLoading))
            .circleCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }

    private fun getRequestListener(onFinishLoading: () -> Unit) =
        object : RequestListener<Drawable> {
            override fun onLoadFailed(
                exception: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onFinishLoading()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onFinishLoading()
                return false
            }
        }
}