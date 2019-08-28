package com.kartum.custom

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import com.kartum.R


/**
 * Created by mgod on 5/27/15.
 *
 * Simple custom view example to show how to get selected events from the token
 * view. See ContactsCompletionView and user_emaill for usage
 */
class UserEmailTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}


    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        setCompoundDrawablesWithIntrinsicBounds(0, 0, if (selected) R.drawable.ic_cancel_red_18dp else 0, 0)
    }
}
