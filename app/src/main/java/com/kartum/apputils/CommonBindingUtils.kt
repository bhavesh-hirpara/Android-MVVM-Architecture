package com.kartum.apputils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter


object CommonBindingUtils {

    @BindingAdapter("imageResource")
    fun setImageResource(imageView: AppCompatImageView,resId: Int) {
        imageView.setImageResource(resId)
    }


}