package com.kartum.ui.login.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FbRes {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("picture")
    @Expose
    var picture: Picture? = null

    inner class Picture {

        @SerializedName("data")
        @Expose
        var data: Data? = null

    }

    inner class Data {

        @SerializedName("is_silhouette")
        @Expose
        var isSilhouette: Boolean = false
        @SerializedName("url")
        @Expose
        var url: String? = null

    }
}