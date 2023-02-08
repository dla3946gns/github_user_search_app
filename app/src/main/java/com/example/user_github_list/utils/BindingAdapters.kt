package com.example.user_github_list.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.user_github_list.R

@BindingAdapter("load")
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl.let {
        Glide
            .with(view)
            .load(it)
            .error(R.mipmap.ic_github)
            .fallback(R.mipmap.ic_github)
            .placeholder(R.mipmap.ic_github)
            .into(view)
    }
}