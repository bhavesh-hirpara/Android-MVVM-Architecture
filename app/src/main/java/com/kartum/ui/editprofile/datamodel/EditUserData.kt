package com.kartum.ui.editprofile.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class EditUserData {

    constructor(statusCode: Int?) : super() {
        this.statusCode = statusCode
    }

    @SerializedName("statusCode")
    @Expose
     var statusCode: Int? = null
    @SerializedName("result")
    @Expose
     var result: Int? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("errors")
    @Expose
    var errors: List<String>? = null
}