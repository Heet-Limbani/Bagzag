package com.bagzag.app.data.repository

import com.bagzag.app.data.pojo.DataWrapper
import com.bagzag.app.data.pojo.Response.SignUpResponse
import com.bagzag.app.data.pojo.ResponseBody
import com.bagzag.app.data.pojo.User
import com.bagzag.app.data.pojo.request.LoginRequest
import com.bagzag.app.data.pojo.request.SignupRequest

interface UserRepository {
    /**
     * Same name of API nca
     */
    suspend fun signup(request: SignupRequest): DataWrapper<SignUpResponse>

}