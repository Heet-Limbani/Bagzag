package com.bagzag.app.ui.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bagzag.app.R
import com.bagzag.app.databinding.AuthFragmentForgotPasswordBinding
import com.bagzag.app.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout

class ForgotPasswordFragment:BaseFragment<AuthFragmentForgotPasswordBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentForgotPasswordBinding {
        return AuthFragmentForgotPasswordBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Email"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Mobile"))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> {
                            binding.emailInput.visibility = android.view.View.VISIBLE
                            binding.mobileInput.visibility = android.view.View.GONE
                            binding.submitButton.text = getString(R.string.submit)
                        }
                        1 -> {
                            binding.emailInput.visibility = android.view.View.GONE
                            binding.mobileInput.visibility = android.view.View.VISIBLE
                            binding.submitButton.text = getString(R.string.get_opt)
                        }
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?){}
            override fun onTabReselected(tab: TabLayout.Tab?){}
        })

        binding.submitButton.setOnClickListener {
            val fragment = DialogSuccess()
            val bundle = Bundle()
            bundle.putString("heading",getString(R.string.check_your_email))
            bundle.putString("subHeading",getString(R.string.dialog_description))
            bundle.putString("button",getString(R.string.ok))
            bundle.putString("navigation",WelcomeScreenFragment::class.java.name)

            fragment.arguments = bundle
            fragment.show(childFragmentManager,"DialogSuccess")
        }
    }
}