package com.bagzag.app.data.pojo.Response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val country_code: String,
    val mobile: String,
    val login_type: String,
    val user_token: String,
    val device_token: String,
    val last_login: String,
    val step: Int,
    val is_active: Int,
    val is_deleted: Int
)