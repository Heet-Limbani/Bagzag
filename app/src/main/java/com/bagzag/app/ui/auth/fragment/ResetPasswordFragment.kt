package com.bagzag.app.ui.auth.fragment

import android.text.InputType
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import com.bagzag.app.R
import com.bagzag.app.databinding.AuthFragmentResetPasswordBinding
import com.bagzag.app.ui.activity.DashboardActivity
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
                DashboardActivity::class.java,
                HomeFragment::class.java
            ).byFinishingCurrent().start()
        }
    }
}