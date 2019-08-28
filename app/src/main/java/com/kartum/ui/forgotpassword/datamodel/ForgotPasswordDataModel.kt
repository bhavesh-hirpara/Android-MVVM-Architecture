package com.kartum.ui.forgotpassword.datamodel

import androidx.lifecycle.MutableLiveData
import com.kartum.datasource.UserRepository
import com.kartum.network.APIClient
import com.kartum.network.APIinterface

class ForgotPasswordDataModel {

    constructor()
    constructor(EmailId: String?) {
        this.EmailId = EmailId
    }

    var EmailId: String? = null

    fun forgotPassword(): MutableLiveData<ForgotPasswordData> {
        val apInterface: APIinterface = APIClient.newRequestRetrofit().create(APIinterface::class.java)
        val userRepository = UserRepository(apInterface)
        return userRepository.forgotPassword(this)
    }
}