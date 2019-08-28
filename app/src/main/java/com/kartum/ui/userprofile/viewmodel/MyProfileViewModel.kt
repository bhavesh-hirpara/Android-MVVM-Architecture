package com.kartum.ui.userprofile.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.kartum.R
import com.kartum.apputils.Constant
import com.kartum.apputils.Utils
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivityMyProfileBinding
import com.kartum.interfaces.TopBarClickListener
import com.kartum.ui.editprofile.view.EditProfileActivity
import com.kartum.ui.edituserdetails.view.EditUserDetailsActivity


/**
 * Created by Kartum Infotech (Bhavesh Hirpara) on 02-Apr-19.
 */
class MyProfileViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityMyProfileBinding
    private lateinit var mContext: Context

    fun setBinder(binder: ActivityMyProfileBinding) {
        this.binder = binder
        this.mContext = binder.root.context

        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        this.binder.topbar.isBackShow = true
        init()
    }

    private fun init() {


        binder.topbar.tvTitleText.text = mContext.getString(R.string.profile)
        binder.topbar.tvTitleText.isAllCaps = true

        var layoutManager = LinearLayoutManager(mContext)
        binder.rvMyVehicle.layoutManager = layoutManager

    }

    inner class ViewClickHandler() {

        fun onEditClick() {
            try {
                val i = Intent(mContext, EditProfileActivity::class.java)
                mContext.startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onEmailChangeClick(view: View) {
            try {
                val i = Intent(mContext, EditUserDetailsActivity::class.java)
                i.putExtra(Constant.FROM, Constant.EMAIL_CHANGE)
                mContext.startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onPhoneChangeClick(view: View) {
            try {
                val i = Intent(mContext, EditUserDetailsActivity::class.java)
                i.putExtra(Constant.FROM, Constant.PHONE_CHANGE)
                mContext.startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onAddVehicleChangeClick(view: View) {
           /* try {
                val i = Intent(mContext, AddVehicleActivity::class.java)
                i.putExtra(Constant.FROM_PROFILE, true)
                mContext.startActivity(i)
                (mContext as Activity).finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }*/
        }

    }


    inner class SlideMenuClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
                /*Handle back pressed*/
                (mContext as Activity).finish()
            }
        }
    }

}