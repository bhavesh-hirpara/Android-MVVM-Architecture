package com.kartum.ui.forgotpassword.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ForgotPasswordData {

    constructor(statusCode: Int?) : super() {
        this.statusCode = statusCode
    }
    @SerializedName("result")
    @Expose
    var result: Any? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null
}