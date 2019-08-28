package com.kartum.ui.forgotpassword.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.kartum.apputils.Constant
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivityForgotPasswordBinding
import com.kartum.interfaces.CallbackListener
import com.kartum.ui.forgotpassword.datamodel.ForgotPasswordData
import com.kartum.ui.forgotpassword.datamodel.ForgotPasswordDataModel
import com.kartum.ui.forgotpassword.view.ForgotPasswordActivity
import com.kartum.ui.login.view.LoginActivity
import com.kartum.validator.EmailValidator

class ForgotPasswordViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityForgotPasswordBinding
    private lateinit var forgotPasswordDataModel: ForgotPasswordDataModel
    private lateinit var mContext: Context

    fun setBinder(binder: ActivityForgotPasswordBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        init()
    }

    private fun init() {
        forgotPasswordDataModel = ForgotPasswordDataModel()
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
    }

    fun afterEmailTextChanged(s: CharSequence) {
        forgotPasswordDataModel.EmailId = s.toString()
    }


    fun isValidate(): Boolean {
        val emailValidator = EmailValidator(forgotPasswordDataModel.EmailId)
        if (!emailValidator.isValid()) {
            showToast(emailValidator.msg!!)
            return false
        }
        return true
    }

    private fun doContinue(view: View) {
        isInternetAvailable(getContext(), object : CallbackListener {
            override fun onSuccess() {
                showDialog("", (mContext as ForgotPasswordActivity))
                binder.isLoading = true

                forgotPasswordDataModel.forgotPassword().observeForever { forgotPasswordData ->
                    if (binder.isLoading!!) {
                        binder.isLoading = false
                        dismissDialog()
                    }
                    onCallResult(forgotPasswordData, view)
                }
            }

            override fun onCancel() {
            }

            override fun onRetry() {
                doContinue(view)
            }
        })
    }

    private fun onCallResult(forgotPasswordData: ForgotPasswordData?, view: View) {    /* Change the Model's of the methods*/
        try {
            if (forgotPasswordData != null) {
                when {
                    forgotPasswordData.statusCode == Constant.RESPONSE_SUCCESS_CODE -> {


                        /*
                        * Moving to 'LoginActivity' */

                        val i = Intent(mContext, LoginActivity::class.java)
                        mContext.startActivity(i)


                    }
                    forgotPasswordData.statusCode == Constant.RESPONSE_FAILURE_CODE -> {
//                        showToast(forgotPasswordData.operationResult!!.responseMessage!!)
                        resetForm()
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
        resetForm()
    }

    private fun resetForm() {
        binder.editLoginEmail.setText("")
    }


    inner class ViewClickHandler() {
        fun onContinueClick(view: View) {
            try {
                if (isValidate()) {
                    doContinue(view)
                }
/*
                val i = Intent(mContext, LoginActivity::class.java)
                mContext.startActivity(i)
*/
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

