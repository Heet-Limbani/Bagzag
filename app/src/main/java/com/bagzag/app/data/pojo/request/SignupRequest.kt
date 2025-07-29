package com.bagzag.app.data.pojo.request

import com.bagzag.app.data.pojo.CountryCode
import com.google.gson.annotations.SerializedName

data class SignupRequest(

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("country_code")
    val countryCode: String,

    @SerializedName("mobile")
    val mobile:String,

    @SerializedName("login_type")
    val loginType: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("device_token")
    val deviceToken:String,

    @SerializedName("device_type")
    val deviceType: String,
)
