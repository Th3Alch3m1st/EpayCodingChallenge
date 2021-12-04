package com.epay.codingchallenge.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.epay.codingchallenge.R
import com.epay.codingchallenge.core.glide.GlideApp

/**
 * Created By Rafiqul Hasan
 */

@BindingAdapter("image_url")
fun ImageView.loadImage(url: String?) {
    GlideApp.with(this)
        .load(url)
        .placeholder(R.drawable.bg_placeholder)
        .error(R.drawable.bg_placeholder)
        .into(this)
}