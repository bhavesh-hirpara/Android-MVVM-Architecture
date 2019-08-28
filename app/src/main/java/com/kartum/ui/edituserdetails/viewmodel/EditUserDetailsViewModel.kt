package com.kartum.ui.edituserdetails.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import com.kartum.R
import com.kartum.apputils.Constant
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivityEditUserDetailsBinding


class EditUserDetailsViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var binder: com.kartum.databinding.ActivityEditUserDetailsBinding
    private lateinit var mContext: Context
    private lateinit var FROM: String

    fun setBinder(binder: ActivityEditUserDetailsBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        init()

    }

    private fun init() {

        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
    }

    public fun initIntentParam(value: String) {
        FROM = value
        if (value.equals(Constant.EMAIL_CHANGE)) {
            emailChangingLayout()
        } else if (value.equals(Constant.PHONE_CHANGE)) {
            phoneChangingLayout()
        }
    }

    private fun emailChangingLayout() {
        binder.tvTitle.setText(mContext.getString(R.string.enter_your_email))
        binder.tvDesc.setText(mContext.getString(R.string.verify_link_will))
        binder.textLayout.hint=mContext.getString(R.string.hint_email)
        binder.btnSave.setText(mContext.getString(R.string.send))
    }

    private fun phoneChangingLayout() {
        binder.tvTitle.setText(mContext.getString(R.string.mobile_number))
        binder.tvDesc.setText(mContext.getString(R.string.enter_your_mobile))
        binder.textLayout.hint=mContext.getString(R.string.cap_mobile_number_full)
        binder.btnSave.setText(mContext.getString(R.string.btn_send_otp))

    }

    inner class ViewClickHandler() {

        fun onBackClick(view: View) {
            try {
                (mContext as Activity).finish()


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onSaveClick(view: View) {
            try {
                if (FROM.equals(Constant.PHONE_CHANGE)) {
                    /*Call PhoneNumber change API*/
                } else if (FROM.equals(Constant.EMAIL_CHANGE)) {
                    /*Move to OTP Verification Activity*/
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }


}