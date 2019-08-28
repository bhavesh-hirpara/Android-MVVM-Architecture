package com.kartum.ui.login.datamodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kartum.datasource.UserRepository
import com.kartum.network.APIClient
import com.kartum.network.APIinterface

class LoginDataModel {

    constructor()
    constructor(EmailId: String?, Password: String?) {
        this.email = EmailId
        this.password = Password
    }

    var email: String? = null
    var password: String? = null

    fun login(context:Context): MutableLiveData<LoginData> {
        val apInterface: APIinterface = APIClient.newRequestRetrofit(context).create(APIinterface::class.java)
        val userRepository = UserRepository(apInterface)
        return userRepository.login(this)
    }
}