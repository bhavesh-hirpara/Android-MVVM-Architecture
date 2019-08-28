package com.kartum.ui.changepassword.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kartum.R
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivityChangePasswordBinding
import com.kartum.ui.changepassword.viewmodel.ChangePasswordViewModel

class ChangePasswordActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityChangePasswordBinding = DataBindingUtil.setContentView(activity, R.layout.activity_change_password)
        val changePasswordViewModel: ChangePasswordViewModel = ViewModelProviders.of(activity).get(ChangePasswordViewModel::class.java)
        changePasswordViewModel.setBinder(binding)
    }
}