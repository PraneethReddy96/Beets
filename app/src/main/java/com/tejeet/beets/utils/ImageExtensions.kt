package com.tejeet.beets.utils


import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

fun ShapeableImageView.loadCenterCropImageFromUrl(imageUrl: String?) {

    Glide.with(this)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}

fun loadGlideImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView)
        .load(imageUrl)
        .into(imageView)
}
