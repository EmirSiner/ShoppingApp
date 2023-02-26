package com.example.shoppingapp.utils.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.shoppingapp.utils.Constants

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}