package com.bagzag.app.ui.auth.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bagzag.app.databinding.AuthFragmentWelcomeScreenBinding
import com.bagzag.app.ui.auth.adapter.AdapterWelcomeScreen
import com.bagzag.app.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class WelcomeScreenFragment:BaseFragment<AuthFragmentWelcomeScreenBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentWelcomeScreenBinding {
        return AuthFragmentWelcomeScreenBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        binding.viewPager.adapter = AdapterWelcomeScreen(this)

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
//            tab.text = tabTitles[position]
        }.attach()

        binding.signInButton.setOnClickListener{
            val bottomSheet = LoginBottomSheetFragment()
            bottomSheet.show(childFragmentManager, "LoginBottomSheetFragment")
        }
    }
}