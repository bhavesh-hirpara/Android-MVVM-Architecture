package com.kartum.ui.changepassword.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ChangePasswordData{

    constructor(statusCode: Int?) : super() {
        this.statusCode = statusCode
    }

    @SerializedName("result")
    @Expose
    var result: Int? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null
}