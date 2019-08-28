package com.kartum.datasource

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kartum.apputils.Constant
import com.kartum.apputils.Debug
import com.kartum.network.APIinterface
import com.kartum.network.RetrofitUtils
import com.kartum.ui.changepassword.datamodel.ChangePasswordData
import com.kartum.ui.changepassword.datamodel.ChangePasswordDataModel
import com.kartum.ui.dashboard.datamodel.UserData
import com.kartum.ui.editprofile.datamodel.EditUserData
import com.kartum.ui.editprofile.datamodel.EditUserDataModel
import com.kartum.ui.forgotpassword.datamodel.ForgotPasswordData
import com.kartum.ui.forgotpassword.datamodel.ForgotPasswordDataModel
import com.kartum.ui.login.datamodel.LoginData
import com.kartum.ui.login.datamodel.LoginDataModel
import com.kartum.ui.signup.datamodel.SignUpData
import com.kartum.ui.signup.datamodel.SignUpDataModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(apiInterface: APIinterface) {

    var apiInterface: APIinterface? = apiInterface

    fun login(loginDataModel: LoginDataModel): MutableLiveData<LoginData> {
        val data = MutableLiveData<LoginData>()
        val call = apiInterface!!.loginRepos(loginDataModel)
        Debug.e("API",call.request().url().encodedPath())
        call.enqueue(object : Callback<LoginData> {
            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                data.value = LoginData(Constant.getFailureCode())
            }

            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                try {
                    if (response.isSuccessful) {
                        val loginData = response.body()
                        loginData!!.statusCode = Constant.getSuccessCode()
                        data.value = loginData
                    } else {
                        try {
                            val inputAsString = response.errorBody()!!.source().inputStream().bufferedReader().use { it.readText() }
                            Debug.e("Input",inputAsString)
                            val sb = StringBuilder()
                            sb.append(inputAsString)
                            val loginData = Gson().fromJson<LoginData>(JSONObject(inputAsString).toString(),object : TypeToken<LoginData>() {}.type)
                            loginData.statusCode = Constant.getFailureCode()
                            data.value = loginData
                        } catch (e: Exception) {
                            e.printStackTrace()
                            data.value = LoginData(Constant.getFailureCode())
                        }
                    }
                } catch (e: Exception) {
                    data.value = LoginData(Constant.getFailureCode())
                }
            }
        })
        return data
    }


    fun signUp(signUpDataModel: SignUpDataModel): MutableLiveData<SignUpData> {
        val data = MutableLiveData<SignUpData>()
        val call = apiInterface!!.signUp(signUpDataModel)
        call.enqueue(object : Callback<SignUpData> {
            override fun onFailure(call: Call<SignUpData>, t: Throwable) {
                data.value = SignUpData(Constant.getFailureCode())
            }

            override fun onResponse(call: Call<SignUpData>, response: Response<SignUpData>) {
                try {
                    if (response.isSuccessful) {
                        val signUpData = response.body()
                        signUpData!!.statusCode = Constant.getSuccessCode()
                        data.value = signUpData
                        Debug.e("signUpData",signUpData.toString())
                    } else {
                        try {
                            val inputAsString = RetrofitUtils.getResponseString(response.errorBody())
                            val signUpData = Gson().fromJson<SignUpData>(JSONObject(inputAsString).toString(),object : TypeToken<SignUpData>() {}.type)
                            signUpData.statusCode = Constant.getFailureCode()
                            data.value = signUpData
                        } catch (e: Exception) {
                            e.printStackTrace()
                            data.value = SignUpData(Constant.getFailureCode())
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    data.value = SignUpData(Constant.getFailureCode())
                }
            }
        })
        return data
    }

    fun forgotPassword(forgotPasswordDataModel: ForgotPasswordDataModel): MutableLiveData<ForgotPasswordData> {
        val data = MutableLiveData<ForgotPasswordData>()
        val call = apiInterface!!.forgotPassword(forgotPasswordDataModel)
        call.enqueue(object : Callback<ForgotPasswordData> {
            override fun onFailure(call: Call<ForgotPasswordData>, t: Throwable) {
                data.value = ForgotPasswordData(Constant.getFailureCode())
            }

            override fun onResponse(call: Call<ForgotPasswordData>, response: Response<ForgotPasswordData>) {
                try {
                    if (response.isSuccessful) {
                        val signUpData = response.body()
                        data.value = signUpData
                    } else {
                        try {
                            val inputAsString = RetrofitUtils.getResponseString(response.errorBody())
                            var forgotPasswordData = Gson().fromJson<ForgotPasswordData>(JSONObject(inputAsString).toString(),object : TypeToken<ForgotPasswordData>() {}.type)
                            forgotPasswordData.statusCode = Constant.getFailureCode()
                            data.value = forgotPasswordData
                        } catch (e: Exception) {
                            e.printStackTrace()
                            data.value = ForgotPasswordData(Constant.getFailureCode())
                        }
                    }
                } catch (e: Exception) {
                    data.value = ForgotPasswordData(Constant.getFailureCode())
                }
            }
        })
        return data
    }


    fun getUserList(page:Int): MutableLiveData<UserData> {
        val data = MutableLiveData<UserData>()
        val call = apiInterface!!.getUserList(page)
        call.enqueue(object : Callback<UserData> {
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                data.value = UserData(Constant.getFailureCode())
            }

            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                try {
                    if (response.isSuccessful) {
                        val userData = response.body()
                        data.value = userData
                    } else {
                        try {
                            val inputAsString = RetrofitUtils.getResponseString(response.errorBody())
                            var userData = Gson().fromJson<UserData>(JSONObject(inputAsString).toString(), object : TypeToken<UserData>() {}.type)
                            data.value = userData
                        } catch (e: Exception) {
                            e.printStackTrace()
                            data.value = UserData(Constant.getFailureCode())
                        }
                    }
                } catch (e: Exception) {
                    data.value = UserData(Constant.getFailureCode())
                }
            }
        })
        return data
    }

    fun editProfile(editUserDataModel: EditUserDataModel): MutableLiveData<EditUserData> {
            val data = MutableLiveData<EditUserData>()
            val call = apiInterface!!.editProfile(editUserDataModel)
            call.enqueue(object : Callback<EditUserData> {
                override fun onFailure(call: Call<EditUserData>, t: Throwable) {
                    data.value = EditUserData(Constant.getFailureCode())
                }

                override fun onResponse(call: Call<EditUserData>, response: Response<EditUserData>) {
                    try {
                        if (response.isSuccessful) {
                            val signUpData = response.body()
                            data.value = signUpData
                        } else {
                            try {
                                val inputAsString = RetrofitUtils.getResponseString(response.errorBody())
                                var forgotPasswordData = Gson().fromJson<EditUserData>(JSONObject(inputAsString).toString(), object : TypeToken<EditUserData>() {}.type)
                                forgotPasswordData.statusCode = Constant.getFailureCode()
                                data.value = forgotPasswordData
                            } catch (e: Exception) {
                                e.printStackTrace()
                                data.value = EditUserData(Constant.getFailureCode())
                            }
                        }
                    } catch (e: Exception) {
                        data.value = EditUserData(Constant.getFailureCode())
                    }
                }
            })
            return data
        }

    fun changePassword(changePasswordDataModel: ChangePasswordDataModel): MutableLiveData<ChangePasswordData> {
            val data = MutableLiveData<ChangePasswordData>()
            val call = apiInterface!!.changePassword(changePasswordDataModel)
            call.enqueue(object : Callback<ChangePasswordData> {
                override fun onFailure(call: Call<ChangePasswordData>, t: Throwable) {
                    data.value = ChangePasswordData(Constant.getFailureCode())
                }

                override fun onResponse(call: Call<ChangePasswordData>, response: Response<ChangePasswordData>) {
                    try {
                        if (response.isSuccessful) {
                            val signUpData = response.body()
                            data.value = signUpData
                        } else {
                            try {
                                val inputAsString = RetrofitUtils.getResponseString(response.errorBody())
                                var changePasswordData = Gson().fromJson<ChangePasswordData>(JSONObject(inputAsString).toString(), object : TypeToken<ChangePasswordData>() {}.type)
                                changePasswordData.statusCode = Constant.getFailureCode()
                                data.value = changePasswordData
                            } catch (e: Exception) {
                                e.printStackTrace()
                                data.value = ChangePasswordData(Constant.getFailureCode())
                            }
                        }
                    } catch (e: Exception) {
                        data.value = ChangePasswordData(Constant.getFailureCode())
                    }
                }
            })
            return data
        }

}





