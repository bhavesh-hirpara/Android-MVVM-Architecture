package com.kartum.ui.otp.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.google.gson.Gson
import com.kartum.apputils.Constant
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivitySendOtpBinding
import com.kartum.ui.otp.view.VerifyOTPActivity
import com.kartum.ui.signup.datamodel.SignUpDataModel

class SendOTPViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivitySendOtpBinding
    private lateinit var mContext: Context
    private var socialLogin: SignUpDataModel? = null


    fun setBinder(binder: ActivitySendOtpBinding,socialLogin: SignUpDataModel?) {
        this.binder = binder
        this.mContext = binder.root.context
        this.socialLogin = SignUpDataModel()
        this.socialLogin = socialLogin
        init()
    }

    private fun init() {
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
    }

    inner class ViewClickHandler() {
        fun onSendClick() {
            try {
                val i = Intent(mContext,VerifyOTPActivity::class.java)
                if (socialLogin != null)
                    i.putExtra(Constant.ISSOCIALLOGIN,Gson().toJson(socialLogin))
                mContext.startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun onBackClick(view: View) {
            try {
                ((mContext) as Activity).finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }
}

