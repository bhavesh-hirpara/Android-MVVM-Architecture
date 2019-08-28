package com.kartum.ui.changepassword.datamodel

import android.content.Context
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.kartum.datasource.UserRepository
import com.kartum.network.APIClient
import com.kartum.network.APIinterface

class ChangePasswordDataModel(){

    @Bindable
    var NewPassword: String? = null
    @Bindable
    var ConfirmPassword: String? = null

    constructor(NewPassword: String?,ConfirmPassword: String?) : this() {
        this.NewPassword = NewPassword
        this.ConfirmPassword = ConfirmPassword
    }


    fun changePassword(context: Context): MutableLiveData<ChangePasswordData> {
        val apInterface: APIinterface = APIClient.newRequestRetrofit(context).create(APIinterface::class.java)
        val userRepository = UserRepository(apInterface)
        return userRepository.changePassword(this)
    }
}