package com.bagzag.app.ui.auth.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bagzag.app.databinding.AuthFragmentPhoneVerificationBinding
import com.bagzag.app.ui.base.BaseFragment

class PhoneVerificationFragment:BaseFragment<AuthFragmentPhoneVerificationBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentPhoneVerificationBinding {
        return AuthFragmentPhoneVerificationBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

    }
}