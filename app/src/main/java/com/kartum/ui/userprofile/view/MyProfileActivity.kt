package com.kartum.ui.userprofile.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kartum.R
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivityMyProfileBinding
import com.kartum.ui.userprofile.viewmodel.MyProfileViewModel

class MyProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMyProfileBinding = DataBindingUtil.setContentView(activity,R.layout.activity_my_profile)
        setStatusBarGradiant(activity)
        val myProfileViewModel:MyProfileViewModel = ViewModelProviders.of(activity).get(MyProfileViewModel::class.java)
        myProfileViewModel.setBinder(binding)

    }
}
