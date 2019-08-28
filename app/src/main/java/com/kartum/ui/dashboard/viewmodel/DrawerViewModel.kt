package com.kartum.ui.dashboard.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kartum.R
import com.kartum.apputils.MenuItem
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivityDashboardBinding
import com.kartum.ui.changepassword.view.ChangePasswordActivity
import com.kartum.ui.contactus.view.ContactUsActivity
import com.kartum.ui.dashboard.utils.SideMenuAdapter
import com.kartum.ui.userprofile.view.MyProfileActivity
import java.util.*


open class DrawerViewModel(application: Application) : BaseViewModel(application) {

    val data = ArrayList<MenuItem>()
    private lateinit var binder: ActivityDashboardBinding
    lateinit var mContext: Context

    fun setBinder(binder: ActivityDashboardBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        initDrawer()
    }

    private fun initDrawer() {

        val linearLayoutManager = LinearLayoutManager(getContext())

        binder.drawer.rvMenuList.layoutManager = linearLayoutManager
        val mAdapter = SideMenuAdapter(getContext())
        mAdapter.setEventListener(object : SideMenuAdapter.EventListener {
            override fun onMenuItemClick(position: Int,view: View) {
                mAdapter.changeSelectedItemUi(position)
                binder.drawerLayout.closeDrawer(GravityCompat.START)
                onMenuItemClicked(mAdapter.getItem(position).menuId,view)
            }
        })

        binder.drawer.rvMenuList.adapter = mAdapter
        sideDrawerList()
        mAdapter.addAll(data)
    }


    private fun sideDrawerList() {
        data.add(MenuItem("1",R.drawable.ic_account_white,getLabelText(R.string.my_profile),true))
        data.add(MenuItem("4",R.drawable.ic_event_white_24dp,getLabelText(R.string.change_password)))
        data.add(MenuItem("5",R.drawable.ic_location_on_white,getLabelText(R.string.sign_out)))
        data.add(MenuItem("8",R.drawable.ic_location_on_white,getLabelText(R.string.contact_us)))
        data.add(MenuItem("9",R.drawable.ic_location_on_white,getLabelText(R.string.help)))
        data.add(MenuItem("10",R.drawable.ic_location_on_white,getLabelText(R.string.FAQ)))
        data.add(MenuItem("11",R.drawable.ic_location_on_white,getLabelText(R.string.get_know)))
        data.add(MenuItem("12",R.drawable.ic_location_on_white,getLabelText(R.string.privacy)))


    }

    private fun onMenuItemClicked(menuId: String,view: View) {
        when (menuId) {
            "1" -> {
                hideMenu(true)
                val i = Intent(mContext,MyProfileActivity::class.java)
                mContext.startActivity(i)
            }
            "2" -> {
                hideMenu(true)
            }
            "3" -> hideMenu(true)
            "4" -> {
                hideMenu(true)
                val i = Intent(mContext,ChangePasswordActivity::class.java)
                mContext.startActivity(i)
            }
            "5" -> {
                hideMenu(true)
                confirmLogout()
            }
            "6" -> hideMenu(true)
            "7" -> hideMenu(true)
            "8" -> {
                hideMenu(true)
                val i = Intent(mContext,ContactUsActivity::class.java)
                mContext.startActivity(i)
            }
            "9" -> hideMenu(true)
            "10" -> hideMenu(true)
            "11" -> hideMenu(true)
            "12" -> hideMenu(true)
        }
    }

    private fun hideMenu(b: Boolean) {
        try {
            if (b)
                binder.drawerLayout.closeDrawer(GravityCompat.START)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    var logoutDialog: androidx.appcompat.app.AlertDialog? = null

    private fun confirmLogout() {

        if (logoutDialog == null) {

            val pd: androidx.appcompat.app.AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder((binder.root.context as Activity),R.style.MyAlertDialogStyle)
            pd.setTitle(getLabelText(R.string.logout_title))
            pd.setMessage(getLabelText(R.string.logout_msg))
            pd.setNegativeButton(getLabelText(R.string.btn_no)) { dialog,which ->
                logoutDialog!!.dismiss()
            }
            pd.setPositiveButton(getLabelText(R.string.btn_yes)) { dialog,which ->
                binder.isLoading = true
                logout()
                logoutDialog!!.dismiss()
            }

            logoutDialog = pd.create()

        }
        logoutDialog!!.show()

        val btnPositive = logoutDialog!!.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
        btnPositive.setTextColor(binder.root.context!!.resources.getColor(R.color.primary))

        val btnNegative = logoutDialog!!.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
        btnNegative.setTextColor(binder.root.context!!.resources.getColor(R.color.primary))


    }

}
