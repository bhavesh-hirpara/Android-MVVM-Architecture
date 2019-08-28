package com.kartum.network

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
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface APIinterface {

    @POST("api/login")
    fun loginRepos(@Body loginDataModel: LoginDataModel): Call<LoginData>

    @POST("api/register")
    fun signUp(@Body signUpDataModel: SignUpDataModel): Call<SignUpData>

    @GET("api/users")
    fun getUserList(@Query ("page")page: Int): Call<UserData>

    /*Below are of Previous project*/


    @POST("authentication/forgotpassword")
    fun forgotPassword(@Body forgotPasswordDataModel: ForgotPasswordDataModel): Call<ForgotPasswordData>


    /* @GET("authentication/loggedinuserdetails")
     fun getUserProfile(): Call<UserData>
 */
    @POST("registration/updateprofile")
    fun editProfile(@Body editUserDataModel: EditUserDataModel): Call<EditUserData>

    @POST("authentication/changepassword")
    fun changePassword(@Body changePasswordDataModel: ChangePasswordDataModel): Call<ChangePasswordData>


}