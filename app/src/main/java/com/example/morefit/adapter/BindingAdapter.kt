package com.example.morefit.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.morefit.R

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(s: String) {
    val thumbnailBaseUrl = "http://img.youtube.com/vi/"

    var url = ""
    url = if (s.contains('=')) {
        s.substring(s.indexOf('='), s.length)
    } else {
        s.substring(17, s.length)
    }

    val imageUrl = "$thumbnailBaseUrl$url/0.jpg"
    Log.w("URL ", imageUrl)

    this.load(imageUrl) {
        placeholder(resources.getDrawable(R.drawable.more_fit_logo))
        crossfade(true)
        error(R.drawable.more_fit_logo)
    }
}