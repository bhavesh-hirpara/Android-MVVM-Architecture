package com.kartum.ui.editprofile.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kartum.R
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivityEditProfileBinding
import com.kartum.ui.editprofile.viewmodel.EditProfileViewModel


class EditProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityEditProfileBinding = DataBindingUtil.setContentView(activity,R.layout.activity_edit_profile)
        setStatusBarGradiant(activity)
        val editProfileViewModel: EditProfileViewModel = ViewModelProviders.of(activity).get(EditProfileViewModel::class.java)
        editProfileViewModel.setBinder(binding)
    }
}