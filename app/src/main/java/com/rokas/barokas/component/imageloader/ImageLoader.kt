package com.rokas.barokas.component.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.rokas.barokas.R
import javax.inject.Inject

class ImageLoader @Inject constructor() {
    fun loadRoundedImage(imageView: ImageView, url: String) {
        Glide
            .with(imageView.context)
            .load(url)
            .circleCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }
}