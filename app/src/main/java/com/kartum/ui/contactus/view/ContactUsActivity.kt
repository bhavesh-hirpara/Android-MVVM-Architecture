package com.kartum.ui.contactus.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kartum.R
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivityContactUsBinding

class ContactUsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityContactUsBinding = DataBindingUtil.setContentView(activity,R.layout.activity_contact_us)
        setStatusBarGradiant(activity)

    }
}
