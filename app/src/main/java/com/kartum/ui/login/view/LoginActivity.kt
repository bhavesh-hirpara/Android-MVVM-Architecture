package com.kartum.ui.login.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kartum.R
import com.kartum.apputils.Debug
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivityLoginBinding
import com.kartum.ui.login.viewmodel.LoginViewModel


class LoginActivity : BaseActivity() {
    private var loginViewModel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(activity, R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(activity).get(LoginViewModel::class.java)
        loginViewModel!!.setBinder(binding)

        if (Debug.DEBUG) {
            binding.editLoginEmail.setText("eve.holt@reqres.in")
            binding.editLoginPass.setText("cityslicka")
        }
    }


}
