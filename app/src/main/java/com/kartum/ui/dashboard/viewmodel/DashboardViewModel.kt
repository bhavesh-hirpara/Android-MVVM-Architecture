package com.kartum.ui.dashboard.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kartum.R
import com.kartum.apputils.Debug
import com.kartum.apputils.EndlessRecyclerViewScrollListener
import com.kartum.apputils.Utils
import com.kartum.base.view.BaseActivity
import com.kartum.base.viewmodel.BaseViewModel
import com.kartum.databinding.ActivityDashboardBinding
import com.kartum.interfaces.CallbackListener
import com.kartum.interfaces.TopBarClickListener
import com.kartum.ui.dashboard.datamodel.UserDataModel
import com.kartum.ui.dashboard.utils.UserAdapter
import com.kartum.ui.userprofile.view.MyProfileActivity

class DashboardViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityDashboardBinding
    lateinit var mContext: Context
    private lateinit var userDataModel: UserDataModel
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var userAdapter: UserAdapter
    var page = 1

    fun setBinder(binder: ActivityDashboardBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        userDataModel = UserDataModel()
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        this.binder.topbar.isShowSearch = true
        init()
        initDashboard()
    }

    private fun init() {
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()

        binder.topbar.tvTitleText.text = mContext.getString(R.string.home)
    }

    private fun initDashboard() {
        mLayoutManager = LinearLayoutManager(mContext)
        binder.rvMenus.layoutManager = mLayoutManager

        userAdapter = UserAdapter(mContext)
        binder.rvMenus.adapter = userAdapter
        userAdapter.setEventListener(object : UserAdapter.EventListener {
            override fun onItemClick(pos: Int) {
                var item = userAdapter.getItem(pos)

            }

        })
        initEndlessList()
        getUserList()

    }

    private fun getUserList() {
        isInternetAvailable(getContext(),object : CallbackListener {
            override fun onSuccess() {
                showDialog("",(mContext as BaseActivity))
                binder.isLoading = true
                userDataModel.getUser(mContext,page).observeForever { userData ->
                    if (binder.isLoading!!) {
                        binder.isLoading = false
                        dismissDialog()
                    }
                    if (userData.data.isNullOrEmpty().not()) {
                        userAdapter.addAll(userData.data!!,page)
                    }
                }
            }

            override fun onCancel() {
            }

            override fun onRetry() {
                getUserList()
            }
        })
    }

    inner class ViewClickHandler {

        fun onNotificationClick(view: View) {
            try {
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    private fun initEndlessList() {
        binder.rvMenus.addOnScrollListener(object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page_: Int,totalItemsCount: Int) {
                Debug.e("onLoadMore","onLoadMore")
                page = page.plus(1)
                getUserList()
            }
        })
    }

    inner class SlideMenuClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?,value: String?) {
            Utils.hideKeyBoard(getContext(),view!!)
            if (value.equals(getLabelText(R.string.menu))) {
                try {
                    if (binder.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        binder.drawerLayout.closeDrawer(GravityCompat.START)
                    } else {
                        binder.drawerLayout.openDrawer(GravityCompat.START)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (value.equals(getLabelText(R.string.myschedule))) {

                /*Move to My Schedule Activity*/

            } else if (value.equals(getLabelText(R.string.addevent))) {
                val i = Intent(mContext,MyProfileActivity::class.java)
                mContext.startActivity(i)
            }
        }
    }


}

