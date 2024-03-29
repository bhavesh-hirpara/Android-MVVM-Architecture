package com.kartum.base.view

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kartum.R

abstract class BaseFragment : Fragment() {

    var tvTitleText: TextView? = null
    fun setTitle(view: View,text: String) {
        if (tvTitleText == null)
            tvTitleText = view.findViewById(R.id.tvTitleText)
        tvTitleText!!.setText(text)
    }

}