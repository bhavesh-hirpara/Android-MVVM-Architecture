package com.kartum.ui.signup.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.kartum.apputils.Constant
import com.kartum.apputils.EditorClickClickListener
import com.kartum.apputils.Utils
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivitySignupBinding
import com.kartum.interfaces.CallbackListener
import com.kartum.ui.login.view.LoginActivity
import com.kartum.ui.signup.datamodel.SignUpData
import com.kartum.ui.signup.datamodel.SignUpDataModel
import com.kartum.ui.signup.view.SignUpActivity
import com.kartum.validator.EmailValidator
import com.kartum.validator.PasswordValidator

class SignUpViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var signUpDataModel: SignUpDataModel
    private lateinit var binder: ActivitySignupBinding
    private lateinit var mContext: Context


    fun setBinder(binder: ActivitySignupBinding) {
        signUpDataModel = SignUpDataModel()
        this.binder = binder
        this.mContext = binder.root.context
        init()
    }

    private fun init() {
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.editFirstName.setOnEditorActionListener(EditorClickClickListener(binder.btnSignUp))
        binder.editLastName.setOnEditorActionListener(EditorClickClickListener(binder.btnSignUp))
        binder.editLoginEmail.setOnEditorActionListener(EditorClickClickListener(binder.btnSignUp))
        binder.editLoginPass.setOnEditorActionListener(EditorClickClickListener(binder.btnSignUp))
        binder.editMobileNumber.setOnEditorActionListener(EditorClickClickListener(binder.btnSignUp))
    }


    fun afterEmailTextChanged(s: CharSequence) {
        signUpDataModel.email = s.toString()
    }

    fun afterPasswordTextChanged(s: CharSequence) {
        signUpDataModel.password = s.toString()
    }


    fun doSignUp(view: View) {
        isInternetAvailable(getContext(), object : CallbackListener {
            override fun onSuccess() {
                showDialog("", (mContext as SignUpActivity))
                binder.isLoading = true
                signUpDataModel.signUp(mContext).observeForever { signUpData ->
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

                        /*
                        * Storing the 'UserId' into preference and, Moving to 'SelectSignatureActivity' */

                        Utils.setPref((getApplication() as Application).applicationContext, Constant.UID, signUpData.id!!)
//                        val i = Intent(mContext, SelectSignatureActivity::class.java)
//                        mContext.startActivity(i)

                        (mContext as Activity).finish()
                    }
                    signUpData.statusCode == Constant.RESPONSE_FAILURE_CODE -> {
                        showToast(signUpData.error!!)
                        resetSignUpForm()
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
        resetSignUpForm()
    }

    private fun resetSignUpForm() {
        /*
        * Reset the fields, Here */

        binder.editLoginPass.setText("")
        binder.editLoginEmail.setText("")
        binder.editFirstName.setText("")
        binder.editLastName.setText("")
        binder.editMobileNumber.setText("")
        binder.editFirstName.requestFocus()
    }


    fun isValidate(): Boolean {
        val emailValidator = EmailValidator(signUpDataModel.email)
        val passwordValidator = PasswordValidator(signUpDataModel.password)
        if (!emailValidator.isValid()) {
            showToast(emailValidator.msg)
            return false
        } else if (!passwordValidator.isValid()) {
            showToast(passwordValidator.msg)
            return false
        }
        return true
    }


    inner class ViewClickHandler() {
        fun onRegisterClick(view: View) {
            try {
                if (isValidate()) {
                    doSignUp(view)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onFacebookClick(view: View) {
            try {
//                val i = Intent(mContext, SendOTPActivity::class.java)
//                mContext.startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onSignInClick(view: View) {
            try {
                val i = Intent(mContext, LoginActivity::class.java)
                mContext.startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}