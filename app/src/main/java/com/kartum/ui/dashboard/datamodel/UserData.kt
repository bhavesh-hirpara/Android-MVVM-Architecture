package com.kartum.ui.dashboard.datamodel

import androidx.databinding.Bindable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class UserData {

    constructor(statusCode: Int?) : super() {
        this.statusCode = statusCode
    }

    @SerializedName("result")
    @Expose
    var result: Result? = null
    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null


    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("per_page")
    @Expose
    var perPage: Int? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    inner class Datum {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("email")
        @Expose
        var email: String? = null
        @SerializedName("first_name")
        @Expose
        var firstName: String? = null
        @SerializedName("last_name")
        @Expose
        var lastName: String? = null
        @SerializedName("avatar")
        @Expose
        var avatar: String? = null

        fun getName():String{
            return firstName +" "+lastName
        }

        fun getFirstLater(): String {
            if (firstName.isNullOrEmpty().not()) {
                return firstName!![0].toString()
            }
            return ""
        }

        fun isFirstLatterDisplay(): Boolean {

            return avatar.isNullOrEmpty()
        }
    }

    inner class Result {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("email")
        @Expose
        var email: String? = null
        @SerializedName("phoneNumber")
        @Expose
        var phoneNumber: String? = null
        @Bindable
        @SerializedName("firstName")
        @Expose
        var firstName: String? = null
        @Bindable
        @SerializedName("lastName")
        @Expose
        var lastName: String? = null
        @SerializedName("status")
        @Expose
        var status: Int? = null
        @SerializedName("isBan")
        @Expose
        var isBan: Boolean? = null
        @SerializedName("accessPermission")
        @Expose
        var accessPermission: Boolean? = null
        @SerializedName("registeredOn")
        @Expose
        var registeredOn: String? = null

    }
}