package com.kartum.ui.forgotpassword.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kartum.R
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivityForgotPasswordBinding
import com.kartum.ui.forgotpassword.viewmodel.ForgotPasswordViewModel


class ForgotPasswordActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityForgotPasswordBinding = DataBindingUtil.setContentView(activity,R.layout.activity_forgot_password)
        val forgotPasswordViewModel: ForgotPasswordViewModel = ViewModelProviders.of(activity).get(ForgotPasswordViewModel::class.java)
        forgotPasswordViewModel.setBinder(binding)
    }

}
