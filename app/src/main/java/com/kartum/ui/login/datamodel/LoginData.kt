package com.kartum.ui.login.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LoginData {

    constructor(statusCode: Int?) : super() {
        this.statusCode = statusCode
    }

    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null


    @SerializedName("token")
    @Expose
    var tokenID: String? = null
    @SerializedName("error")
    @Expose
    var error: String? = null

}