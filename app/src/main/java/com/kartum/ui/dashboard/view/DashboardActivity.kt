package com.kartum.ui.dashboard.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kartum.R
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivityDashboardBinding
import com.kartum.ui.dashboard.viewmodel.DashboardViewModel
import com.kartum.ui.dashboard.viewmodel.DrawerViewModel
import com.mikepenz.materialdrawer.Drawer


class DashboardActivity : BaseActivity() {

    lateinit var result: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDashboardBinding = DataBindingUtil.setContentView(activity, R.layout.activity_dashboard)
        val dashboardViewModel: DashboardViewModel = ViewModelProviders.of(activity).get(DashboardViewModel::class.java)
        val drawerViewModel: DrawerViewModel = ViewModelProviders.of(activity).get(DrawerViewModel::class.java)
        drawerViewModel.setBinder(binding)
        dashboardViewModel.setBinder(binding)
    }

}
