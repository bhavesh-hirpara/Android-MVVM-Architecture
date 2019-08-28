package com.kartum.ui.otp.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kartum.R
import com.kartum.apputils.Constant
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivitySendOtpBinding
import com.kartum.ui.otp.viewmodel.SendOTPViewModel
import com.kartum.ui.signup.datamodel.SignUpDataModel


class SendOTPActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySendOtpBinding = DataBindingUtil.setContentView(activity,R.layout.activity_send_otp)
        val sendOTPViewModel: SendOTPViewModel = ViewModelProviders.of(activity).get(SendOTPViewModel::class.java)

        if(intent != null) {
            if(intent.hasExtra(Constant.ISSOCIALLOGIN)) {
                val isSocialLogin = intent.extras!!.getString(Constant.ISSOCIALLOGIN)
                if (isSocialLogin.isNullOrEmpty()) {
                    val socialLoginData = Gson().fromJson<SignUpDataModel>(isSocialLogin,
                            object : TypeToken<SignUpDataModel>() {}.type)
                    sendOTPViewModel.setBinder(binding,socialLoginData)
                } else {
                    sendOTPViewModel.setBinder(binding,null)
                }
            }else {
                sendOTPViewModel.setBinder(binding,null)
            }
        }
    }

}
