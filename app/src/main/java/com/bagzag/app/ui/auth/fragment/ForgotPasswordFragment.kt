package com.bagzag.app.ui.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bagzag.app.R
import com.bagzag.app.data.clickListner.OnClickCountryCodeCard
import com.bagzag.app.data.pojo.CountryCode
import com.bagzag.app.databinding.AuthFragmentForgotPasswordBinding
import com.bagzag.app.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<AuthFragmentForgotPasswordBinding>() {

    var selectedPosition = 0
    var forgotType = ForgotType.EMAIL

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
                            binding.emailInput.visibility = View.VISIBLE
                            binding.mobileInput.visibility = View.GONE
                            binding.submitButton.text = getString(R.string.submit)
                            forgotType = ForgotType.EMAIL
                        }

                        1 -> {
                            binding.emailInput.visibility = View.GONE
                            binding.mobileInput.visibility = View.VISIBLE
                            binding.submitButton.text = getString(R.string.get_opt)
                            forgotType = ForgotType.MOBILE
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.submitButton.setOnClickListener {

            when (forgotType) {
                ForgotType.EMAIL -> {
                    val fragment = DialogSuccess()
                    val bundle = Bundle()
                    bundle.putString("heading", getString(R.string.check_your_email))
                    bundle.putString("subHeading", getString(R.string.dialog_description))
                    bundle.putString("button", getString(R.string.ok))
                    bundle.putString("navigation", WelcomeScreenFragment::class.java.name)
                    fragment.arguments = bundle
                    fragment.show(childFragmentManager, "DialogSuccess")
                }

                ForgotType.MOBILE -> {
                    val bundle = Bundle()
                    bundle.putString("nextPage", "ResetPasswordFragment")
                    navigator.load(PhoneVerificationFragment::class.java)
                        .replace(true, "PhoneVerificationFragment")
                }
            }
        }

        val countryCodeList = mutableListOf<CountryCode>().apply {
            add(CountryCode(R.drawable.ic_flag_canada, "+1", "Canada"))
            add(CountryCode(R.drawable.ic_flag_afghanistan, "+93", "Afghanistan"))
            add(CountryCode(R.drawable.ic_flag_albania, "+355", "Albania"))
            add(CountryCode(R.drawable.ic_flag_algeria, "+213", "Algeria"))
            add(CountryCode(R.drawable.ic_flag_american_samoa, "+1684", "American Samoa"))
            add(CountryCode(R.drawable.ic_flag_andorra, "+256", "Andorra"))
            add(CountryCode(R.drawable.ic_flag_angola, "+12", "Angola"))
            add(CountryCode(R.drawable.ic_flag_anguilla, "+1345", "Anguilla"))
            add(CountryCode(R.drawable.ic_flag_albania, "+355", "Albania"))
            add(CountryCode(R.drawable.ic_flag_algeria, "+213", "Algeria"))
            add(CountryCode(R.drawable.ic_flag_american_samoa, "+1684", "American Samoa"))
            add(CountryCode(R.drawable.ic_flag_andorra, "+256", "Andorra"))
            add(CountryCode(R.drawable.ic_flag_angola, "+12", "Angola"))
            add(CountryCode(R.drawable.ic_flag_anguilla, "+1345", "Anguilla"))
            add(CountryCode(R.drawable.ic_flag_canada, "+1", "Canada"))
            add(CountryCode(R.drawable.ic_flag_afghanistan, "+93", "Afghanistan"))
        }

        binding.countryCodeLayout.setOnClickListener {
            val fragment = DialogCountryCode()
            val bundle = Bundle()
            bundle.putParcelableArrayList("countryCodeList", ArrayList(countryCodeList))
            fragment.arguments = bundle
            fragment.selectedPosition = selectedPosition
            fragment.onClickCountryCodeCard = object : OnClickCountryCodeCard {
                override fun onClick(countryCode: CountryCode, position: Int, view: View) {
                    selectedPosition = position
                    binding.countryCode.text = countryCode.countryCode
                    binding.countryFlag.setImageResource(countryCode.countryFlag)
                }
            }
            fragment.show(childFragmentManager, "DialogCountryCode")
        }
    }
}

enum class ForgotType {
    EMAIL, MOBILE
}
