package com.kartum.ui.editprofile.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.kartum.R
import com.kartum.apputils.Utils
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivityEditProfileBinding
import com.kartum.interfaces.TopBarClickListener
import com.kartum.ui.dashboard.view.DashboardActivity


class EditProfileViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var binder: com.kartum.databinding.ActivityEditProfileBinding
    private lateinit var mContext: Context

    fun setBinder(binder: ActivityEditProfileBinding) {
        this.binder = binder
        this.mContext = binder.root.context

        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        this.binder.topbar.isBackShow = true
        init()
    }

    private fun init() {

        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()

        binder.topbar.isShowHome = true
        binder.topbar.tvTitleText.text = mContext.getString(R.string.edit_profile)
        binder.topbar.tvTitleText.isAllCaps = true
    }

    inner class ViewClickHandler() {

        fun onSaveClick(view: View) {
            try {


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


    inner class SlideMenuClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
                (mContext as Activity).finish()
            }
            if (value.equals(getLabelText(R.string.home))) {
                val i = Intent(mContext, DashboardActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                mContext.startActivity(i)
                (mContext as Activity).finish()
            }
        }
    }


}