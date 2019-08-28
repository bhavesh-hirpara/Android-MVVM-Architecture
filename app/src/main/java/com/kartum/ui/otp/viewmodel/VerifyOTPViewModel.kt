package com.kartum.ui.otp.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.kartum.apputils.Constant
import com.kartum.apputils.Utils
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivityVerifyOtpBinding
import com.kartum.interfaces.CallbackListener
import com.kartum.ui.signup.datamodel.SignUpData
import com.kartum.ui.signup.datamodel.SignUpDataModel
import com.kartum.ui.signup.view.SignUpActivity

class VerifyOTPViewModel(application: Application) : BaseViewModel(application) {

    private var signUpDataModel: SignUpDataModel? = null
    private lateinit var binder: ActivityVerifyOtpBinding
    private lateinit var mContext: Context

    fun setBinder(binder: ActivityVerifyOtpBinding, socialLogin: SignUpDataModel?) {
        signUpDataModel = socialLogin
        this.binder = binder
        this.mContext = binder.root.context
        init()
    }

    private fun init() {
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
    }

    inner class ViewClickHandler() {
        fun onVerifyClick(view: View) {
            try {
                if (signUpDataModel == null) {
                    val i = Intent(mContext, SignUpActivity::class.java)
                    mContext.startActivity(i)
                    (mContext as Activity).finish()
                } else {
                    doSignUp(view)
                }

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

        fun onResendClick() {
//            try {
//                ((mContext) as Activity).finish()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
        }

    }

    fun doSignUp(view: View) {
        isInternetAvailable(getContext(), object : CallbackListener {
            override fun onSuccess() {
                showDialog("", (mContext as SignUpActivity))
                binder.isLoading = true
                signUpDataModel!!.signUp(mContext).observeForever { signUpData ->
                    if (binder.isLoading!!) {
                        binder.isLoading = false
                        dismissDialog()
                    }

                    onCallResult(signUpData, view)

                }
            }

            override fun onCancel() {
            }

            override fun onRetry() {
                doSignUp(view)
            }
        })
    }

    private fun onCallResult(signUpData: SignUpData?, view: View) {
        try {
            if (signUpData != null) {
                when {
                    signUpData.statusCode == Constant.RESPONSE_SUCCESS_CODE -> {

                        Utils.setPref((getApplication() as Application).applicationContext, Constant.UID, signUpData.id!!)
//                        val i = Intent(mContext, WelcomeAfterSignInActivity::class.java)
//                        mContext.startActivity(i)

                    }
                    signUpData.statusCode == Constant.RESPONSE_FAILURE_CODE -> {
                        showToast(signUpData.error)
                        (mContext as Activity).finish()
                    }
                    else -> {
                        showError()
                    }
                }
            } else {
                showError()
            }
        } catch (e: Exception) {
            showError()
        }
    }

    private fun showError() {
        showHttpError(mContext)
        (mContext as Activity).finish()
    }


}



