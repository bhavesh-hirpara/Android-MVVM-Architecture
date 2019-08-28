package com.kartum.apputils

import android.content.Context
import com.kartum.R


class ValidationToast(private var context: Context) {

    fun showFieldRequiredToast(strResId : Int){
        showToast(String.format(context.getString(R.string.field_required), context.getString(strResId)))
    }

    fun showInvalidFormatToast(strResId : Int){
        showToast(String.format(context.getString(R.string.invalid_format), context.getString(strResId)))
    }

    fun showMinCharactersToast(strResId : Int){
        showToast(String.format(context.getString(R.string.min_format), context.getString(strResId)))
    }

    fun showToast(text: String) {
        Utils.showToast(context, text)
    }
}