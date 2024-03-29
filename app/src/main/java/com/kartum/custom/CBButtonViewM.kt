package com.kartum.custom

import android.content.Context
import android.util.AttributeSet

import com.kartum.apputils.Utils

import androidx.appcompat.widget.AppCompatButton


/**
 * @author VaViAn Labs.
 */
class CBButtonViewM : AppCompatButton {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    fun init() {
        if (!isInEditMode) {
            try {
                //                if (!Locale.getDefault().toString().startsWith("en"))
                typeface = Utils.getBold(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}