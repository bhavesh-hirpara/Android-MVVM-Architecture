package com.kartum.ui.editprofile.datamodel

import android.content.Context
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.kartum.datasource.UserRepository
import com.kartum.network.APIClient
import com.kartum.network.APIinterface

class EditUserDataModel {
    @Bindable
    var FirstName: String? = null
    @Bindable
    var LastName: String? = null
    @Bindable
    var EmailId: String? = null
    @Bindable
    var PhoneNumber: String? = null

    fun updateProfile(context : Context): MutableLiveData<EditUserData> {
        val apInterface: APIinterface = APIClient.newRequestRetrofit(context).create(APIinterface::class.java)
        val userRepository = UserRepository(apInterface)
        return userRepository.editProfile(this)
    }
}