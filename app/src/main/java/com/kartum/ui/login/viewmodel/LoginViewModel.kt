package com.kartum.ui.login.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.kartum.apputils.*
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivityLoginBinding
import com.kartum.interfaces.CallbackListener
import com.kartum.ui.forgotpassword.view.ForgotPasswordActivity
import com.kartum.ui.login.datamodel.FbRes
import com.kartum.ui.login.datamodel.LoginData
import com.kartum.ui.login.datamodel.LoginDataModel
import com.kartum.ui.login.view.LoginActivity
import com.kartum.ui.otp.view.SendOTPActivity
import com.kartum.ui.signup.datamodel.SignUpDataModel
import com.kartum.ui.view.MainActivity
import com.kartum.validator.EmailValidator
import com.kartum.validator.PasswordValidator

class LoginViewModel(application: Application) : BaseViewModel(application) {

    lateinit var activityLoginBinding: ActivityLoginBinding
    private lateinit var loginDataModel: LoginDataModel
    private lateinit var signUpDataModel: SignUpDataModel
    private lateinit var mContext: Context
    lateinit var fbRes: FbRes
    lateinit var googleRes: GoogleSignInAccount
    internal val RC_SIGN_IN = 9001
    private var mGoogleApiClient: GoogleApiClient? = null


    fun setBinder(binder: ActivityLoginBinding) {
        this.activityLoginBinding = binder
        this.mContext = binder.root.context
        loginDataModel = LoginDataModel()
        signUpDataModel = SignUpDataModel()
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.editLoginEmail.setOnEditorActionListener(EditorClickClickListener(binder.btnLogin))
        binder.editLoginPass.setOnEditorActionListener(EditorClickClickListener(binder.btnLogin))
        binder.executePendingBindings()
        init()
    }

    private fun init() {
//        initFacebook()
//        initGoogle()
    }

    fun afterEmailTextChanged(s: CharSequence) {
        loginDataModel.email = s.toString()
    }

    fun afterPasswordTextChanged(s: CharSequence) {
        loginDataModel.password = s.toString()
    }

    private fun doLogin(view: View,isSocialLogin: Boolean) {
        isInternetAvailable(getContext(),object : CallbackListener {
            override fun onSuccess() {
                showDialog("",(mContext as LoginActivity))
                activityLoginBinding.isLoading = true
                loginDataModel.login(mContext).observeForever { loginData ->
                    if (activityLoginBinding.isLoading!!) {
                        activityLoginBinding.isLoading = false
                        dismissDialog()
                    }
                    onCallResult(loginData,isSocialLogin)
                }
            }

            override fun onCancel() {
            }

            override fun onRetry() {
                doLogin(view,isSocialLogin)
            }
        })
    }

    private fun onCallResult(loginData: LoginData?,isSocialLogin: Boolean) = try {
        if (loginData != null) {
                when {

                    loginData.statusCode == Constant.RESPONSE_SUCCESS_CODE -> {

                        Utils.setPref((getApplication() as Application).applicationContext,
                                RequestParamsUtils.AUTHENTICATIONTOKEN,loginData.tokenID!!)
                        Utils.setPref((getApplication() as Application).applicationContext,Constant.UID,loginData.tokenID!!)
                        Utils.setPref((getApplication() as Application).applicationContext,Constant.LOGIN_INFO,Gson().toJson(loginData))

                        /*
                        * Moving to 'WelcomeAfterSignInActivity' */

                        val i = Intent(mContext,MainActivity::class.java)
                        mContext.startActivity(i)
                        (mContext as Activity).finish()
                    }
                    loginData.statusCode == Constant.RESPONSE_FAILURE_CODE -> {
                        showToast(loginData.error!!)
                        resetLoginForm()
                    }
                    loginData.statusCode == Constant.RESPONSE_FAILURE_CODE -> {
                        showToast(loginData.error!!)
                        resetLoginForm()
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

    private fun showError() {
        showHttpError(mContext)
        resetLoginForm()
    }

    private fun resetLoginForm() {
        /*activityLoginBinding.editLoginPass.setText("")
        activityLoginBinding.editLoginEmail.setText("")*/
        activityLoginBinding.editLoginEmail.requestFocus()
    }

    fun isValidate(): Boolean {
        val emailValidator = EmailValidator(loginDataModel.email)
        val passwordValidator = PasswordValidator(loginDataModel.password)
        if (!emailValidator.isValid()) {
            showToast(emailValidator.msg!!)
            return false
        } else if (!passwordValidator.isValidPassword()) {
            showToast(passwordValidator.msg!!)
            return false
        }
        return true
    }

    inner class ViewClickHandler {

        fun onLoginClick(view: View) {
            try {
                if (isValidate()) {
                    doLogin(view,false)
                    /*val i = Intent(mContext, WelcomeAfterSignInActivity::class.java)
                    mContext.startActivity(i)
                    (mContext as Activity).finish()*/
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onFacebookClick() {
            try {
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onGoogleClick() {
            try {
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onForgotPasswordClick() {
            try {
                val i = Intent(mContext,ForgotPasswordActivity::class.java)
                mContext.startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onRegisterClick() {
            try {
                val i = Intent(mContext,SendOTPActivity::class.java)
                mContext.startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    internal fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {

            val account = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                googleRes = account
            }
            Debug.e("TAG","getIdToken: " + account!!.idToken)
            getProfileInformation(account)
            // Signed in successfully, show authenticated UI.
        } catch (e: ApiException) {
            Debug.e("ApiException","signInResult:failed code=" + e.statusCode + " " + e.statusMessage)
        }

        (mContext as Activity).runOnUiThread { dismissDialog() }
    }

    private fun getProfileInformation(acct: GoogleSignInAccount) {
        Debug.e("accessToken","==>" + acct.idToken)
        Debug.e("accessToken","==>" + acct.id)

        loginDataModel.email = acct.id
        loginDataModel.password = ""


        doLogin(activityLoginBinding.root,true)

    }


}


