package com.bagzag.app.ui.auth.viewmodel

import com.bagzag.app.data.pojo.Response.SignUpResponse
import com.bagzag.app.data.pojo.User
import com.bagzag.app.data.pojo.request.LoginRequest
import com.bagzag.app.data.pojo.request.SignupRequest
import com.bagzag.app.data.repository.UserRepository
import com.bagzag.app.ui.base.APILiveData
import com.bagzag.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    val signUpLiveData = APILiveData<SignUpResponse>()

    fun signup(request: SignupRequest) = launch {
        val result = userRepository.signup(request)
        signUpLiveData.value = result
    }
}