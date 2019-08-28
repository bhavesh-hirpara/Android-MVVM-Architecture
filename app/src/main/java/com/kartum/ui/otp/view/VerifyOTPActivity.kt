package com.kartum.ui.otp.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kartum.R
import com.kartum.apputils.Constant
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivityVerifyOtpBinding
import com.kartum.ui.otp.viewmodel.VerifyOTPViewModel
import com.kartum.ui.signup.datamodel.SignUpDataModel

class VerifyOTPActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityVerifyOtpBinding = DataBindingUtil.setContentView(activity,R.layout.activity_verify_otp)
        val verifyOTPViewModel: VerifyOTPViewModel = ViewModelProviders.of(activity).get(VerifyOTPViewModel::class.java)
        if(intent != null) {
            if(intent.hasExtra(Constant.ISSOCIALLOGIN)) {
                val isSocialLogin = intent.extras!!.getString(Constant.ISSOCIALLOGIN)
                if (isSocialLogin.isNullOrEmpty()) {
                    val socialLoginData = Gson().fromJson<SignUpDataModel>(isSocialLogin,
                            object : TypeToken<SignUpDataModel>() {}.type)
                    verifyOTPViewModel.setBinder(binding,socialLoginData)
                } else {
                    verifyOTPViewModel.setBinder(binding,null)
                }
            }else {
                verifyOTPViewModel.setBinder(binding,null)
            }
        }

    }

}
