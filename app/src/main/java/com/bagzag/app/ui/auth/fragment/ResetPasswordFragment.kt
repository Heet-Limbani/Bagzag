package com.bagzag.app.ui.auth.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bagzag.app.databinding.AuthFragmentResetPasswordBinding
import com.bagzag.app.ui.activity.DashboardActivityTwo
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.dashboard.fragment.HomeFragment

class ResetPasswordFragment:BaseFragment<AuthFragmentResetPasswordBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentResetPasswordBinding {
        return AuthFragmentResetPasswordBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        binding.updateButton.setOnClickListener {
            navigator.loadActivity(
                DashboardActivityTwo::class.java,
                HomeFragment::class.java
            ).byFinishingCurrent().start()
        }
    }
}