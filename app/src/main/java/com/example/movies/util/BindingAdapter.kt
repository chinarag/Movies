package com.example.movies.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.movies.R

@BindingAdapter("load_url")
fun loadImage(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        imageView.setImageResource(R.drawable.placeholder)
    } else {
        imageView.loadImageUrl(url)
    }
}


@BindingAdapter("load_url_youtube")
fun loadImageYoutube(imageView: ImageView, key: String?) {
    if (key.isNullOrEmpty()) {
        imageView.setImageResource(R.drawable.placeholder)
    } else {
        imageView.loadImageYoutube(key)
    }
}
/*
fun loadImage(imageView: ImageView, url: String){
    imageView.loadImageUrl(url)
}*/
