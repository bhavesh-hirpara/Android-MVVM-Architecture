package com.kartum.ui.signup.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kartum.R
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivitySignupBinding
import com.kartum.ui.signup.viewmodel.SignUpViewModel


class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(activity,R.layout.activity_signup)
        val signUpViewModel: SignUpViewModel = ViewModelProviders.of(activity).get(SignUpViewModel::class.java)
        signUpViewModel.setBinder(binding)
    }

}
