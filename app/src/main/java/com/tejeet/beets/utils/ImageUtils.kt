package com.andre_max.tiktokclone.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import timber.log.Timber

object ImageUtils {

    fun loadGlideImage(imageView: ImageView, profilePictureUrl: String?) {
        Glide.with(imageView)
            .load(profilePictureUrl)
            .into(imageView)
    }

    fun loadGlideImage(imageView: ImageView, @DrawableRes drawableRes: Int) {
        Glide.with(imageView)
            .load(drawableRes)
            .into(imageView)
    }

    fun loadGlideImage(imageView: ImageView, bitmap: Bitmap) {
        Glide.with(imageView)
            .load(bitmap)
            .into(imageView)
    }

}