package com.kartum.ui.dashboard.datamodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kartum.datasource.UserRepository
import com.kartum.network.APIClient
import com.kartum.network.APIinterface

class UserDataModel {

    fun getUser(context: Context,page:Int): MutableLiveData<UserData> {
        val apiInterface: APIinterface = APIClient.newRequestRetrofit(context).create(APIinterface::class.java)
        val userRepository = UserRepository(apiInterface)
        return userRepository.getUserList(page)
    }

}
