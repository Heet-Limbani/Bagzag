package com.bagzag.app.data.datasource

import com.bagzag.app.data.pojo.DataWrapper
import com.bagzag.app.data.pojo.Response.SignUpResponse
import com.bagzag.app.data.pojo.ResponseBody
import com.bagzag.app.data.pojo.User
import com.bagzag.app.data.pojo.request.LoginRequest
import com.bagzag.app.data.pojo.request.SignupRequest
import com.bagzag.app.data.repository.UserRepository
import com.bagzag.app.data.service.AuthenticationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService) :
    BaseDataSource(), UserRepository {

    override suspend fun signup(request: SignupRequest): DataWrapper<SignUpResponse> {
        return execute{ authenticationService.signup(request) }

    }
}
