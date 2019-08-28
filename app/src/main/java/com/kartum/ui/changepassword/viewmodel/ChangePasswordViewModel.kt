package com.kartum.ui.changepassword.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import com.kartum.R
import com.kartum.apputils.Utils
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivityChangePasswordBinding
import com.kartum.interfaces.TopBarClickListener
import com.kartum.ui.changepassword.datamodel.ChangePasswordDataModel
import com.kartum.validator.PasswordValidator

class ChangePasswordViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityChangePasswordBinding
    lateinit var changePasswordDataModel: ChangePasswordDataModel
    private lateinit var mContext: Context


    fun setBinder(binder: ActivityChangePasswordBinding) {
        changePasswordDataModel = ChangePasswordDataModel()
        this.binder = binder
        mContext=binder.root.context
        init()
    }

    private fun init() {
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
//        binder.editNewPassword.setOnEditorActionListener(EditorClickClickListener(binder.btnResetPassword))
//        binder.editConfirmPassword.setOnEditorActionListener(EditorClickClickListener(binder.btnResetPassword))
    }


    fun afterNewPasswordTextChanged(s: CharSequence) {
        changePasswordDataModel.NewPassword = s.toString()
    }

    fun afterConfirmPasswordTextChanged(s: CharSequence) {
        changePasswordDataModel.ConfirmPassword = s.toString()
    }

    inner class ViewClickHandler() {
        fun onResetPasswordClick(view: View) {
            Utils.hideKeyBoard(getContext(), binder.btnResetPassword)
            if (isValidate()) {
//                doChangePassword(view)
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

    private fun isValidate(): Boolean {
        val newPasswordValidator = PasswordValidator(changePasswordDataModel.NewPassword, "New")
        if (!newPasswordValidator.isValid() ||
                !newPasswordValidator.isValidConfirmPassword(changePasswordDataModel.ConfirmPassword) ||
                newPasswordValidator.isOldPasswordMatchedNewPassword(changePasswordDataModel.NewPassword)) {

            showToast(newPasswordValidator.msg)
            return false
        }
        return true
    }

//    private fun doChangePassword(view: View) {
//        isInternetAvailable(contextWeakReference!!.get()!!, object : CallbackListener {
//            override fun onSuccess() {
//                val context = contextWeakReference!!.get()
//                showDialog("", (context!! as MainActivity))
//                binder.isLoading = true
//                changePasswordDataModel.changePassword(getContext()).observeForever { changePasswordData ->
//                    when {
//                        binder.isLoading!! -> {
//                            binder.isLoading = false
//                            dismissDialog()
//                        }
//                    }
//                    try {
//                        when {
//                            changePasswordData.statusCode == Constant.RESPONSE_SUCCESS_CODE -> {
//                                showToast(changePasswordData.message!!)
//                                logout()
////                                NavigationHelper.navigateGraph(R.navigation.authentication_graph, contextWeakReference!!.get()!!)
//                            }
//                            changePasswordData.statusCode == Constant.RESPONSE_FAILURE_CODE -> {
//                                showToast(changePasswordData.message!!)
//                                resetModel()
//                            }
//                            else -> {
//                                showError()
//                            }
//                        }
//                    } catch (e: Exception) {
//                        showError()
//                    }
//                }
//            }
//
//            override fun onCancel() {
//            }
//
//            override fun onRetry() {
//                doChangePassword(view)
//            }
//        })
//
//    }
//
//    private fun showError() {
//        showHttpError(contextWeakReference!!.get()!!)
//        resetModel()
//    }

    private fun resetModel() {
        changePasswordDataModel.NewPassword = null
        changePasswordDataModel.ConfirmPassword = null
        binder.viewModel = this
        binder.editNewPassword.requestFocus()
    }

    inner class ViewBackClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
//                NavigationHelper.moveUp(view)
            }
        }
    }

}

