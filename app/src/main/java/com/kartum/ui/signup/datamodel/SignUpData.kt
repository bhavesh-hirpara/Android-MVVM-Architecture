package com.kartum.ui.signup.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class SignUpData {

    constructor(statusCode: Int?) : super() {
        this.statusCode = statusCode
    }

    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null

    @SerializedName("token")
    @Expose
    var tokenID: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("error")
    @Expose
    var error: String? = null

}