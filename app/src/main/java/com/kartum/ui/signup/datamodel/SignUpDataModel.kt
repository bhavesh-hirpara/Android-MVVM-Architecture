package com.kartum.ui.signup.datamodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kartum.datasource.UserRepository
import com.kartum.network.APIClient
import com.kartum.network.APIinterface

class SignUpDataModel {

    var email: String? = null
    var password: String? = null

    constructor()

    constructor(email: String?, password: String?) {
        this.email = email
        this.password = password
    }

    fun signUp(context: Context): MutableLiveData<SignUpData> {
        val apInterface: APIinterface = APIClient.newRequestRetrofit(context).create(APIinterface::class.java)
        val userRepository = UserRepository(apInterface)
        return userRepository.signUp(this)
    }
}