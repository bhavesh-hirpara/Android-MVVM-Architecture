package com.kartum.ui.edituserdetails.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kartum.R
import com.kartum.apputils.Constant
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivityEditUserDetailsBinding
import com.kartum.ui.edituserdetails.viewmodel.EditUserDetailsViewModel

class EditUserDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityEditUserDetailsBinding= DataBindingUtil.setContentView(activity, R.layout.activity_edit_user_details)
        setStatusBarGradiant(activity)
        val editUserDetailsViewModel: EditUserDetailsViewModel= ViewModelProviders.of(activity).get(EditUserDetailsViewModel::class.java)
        editUserDetailsViewModel.setBinder(binding)
        editUserDetailsViewModel.initIntentParam(intent.getStringExtra(Constant.FROM))
    }
}
