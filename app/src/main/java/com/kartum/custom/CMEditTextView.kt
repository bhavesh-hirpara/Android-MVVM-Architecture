package com.kartum.custom

import android.content.Context
import androidx.appcompat.widget.AppCompatEditText
import android.util.AttributeSet

import com.kartum.apputils.Utils


/**
 * @author VaViAn Labs.
 */
class CMEditTextView : AppCompatEditText {

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
                typeface = Utils.getMedium(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}