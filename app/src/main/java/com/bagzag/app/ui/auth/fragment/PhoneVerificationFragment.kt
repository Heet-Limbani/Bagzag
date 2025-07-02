package com.bagzag.app.ui.auth.fragment

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.bagzag.app.R
import com.bagzag.app.databinding.AuthFragmentPhoneVerificationBinding
import com.bagzag.app.ui.activity.DashboardActivity
import com.bagzag.app.ui.activity.DashboardActivityTwo
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.dashboard.fragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneVerificationFragment:BaseFragment<AuthFragmentPhoneVerificationBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentPhoneVerificationBinding {
        return AuthFragmentPhoneVerificationBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        val otpFields = listOf<EditText>(
            binding.otpDigitOne,
            binding.otpDigitTwo,
            binding.otpDigitThree,
            binding.otpDigitFour
        )

        setupOtpInputs(otpFields)

        binding.verifyButton.setOnClickListener {
            val nextPage = arguments?.getString("nextPage") ?: "DashboardActivity"
            if(nextPage == "DashboardActivity") {
                navigator.loadActivity(
                    DashboardActivity::class.java,
                    HomeFragment::class.java
                ).byFinishingCurrent().start()
            } else if(nextPage == "ResetPasswordFragment") {
                navigator.load(ResetPasswordFragment::class.java).clearHistory(null).replace(false,"ResetPasswordFragment")
            }
        }
    }

    private fun setupOtpInputs(otpFields: List<EditText>) {

        for (i in otpFields.indices) {
            otpFields[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otpFields.size - 1) {
                        otpFields[i + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrEmpty()) {
                        otpFields[i].background = ContextCompat.getDrawable(requireContext(), R.drawable.drawable_otp_input_selected_grey)
                    } else {
                        otpFields[i].background = ContextCompat.getDrawable(requireContext(), R.drawable.drawable_otp_input_selected)
                    }
                }
            })

            otpFields[i].setOnKeyListener { _, keyCode, event ->
                if (keyCode == android.view.KeyEvent.KEYCODE_DEL && event.action == android.view.KeyEvent.ACTION_DOWN) {
                    if (otpFields[i].text.isEmpty() && i > 0) {
                        otpFields[i - 1].requestFocus()
                        otpFields[i - 1].text.clear()
                    }
                }
                false
            }

            otpFields[i].setOnFocusChangeListener { view, hasFocus ->
                val editText = view as EditText
                if (hasFocus) {
                    view.background = ContextCompat.getDrawable(requireContext(), R.drawable.drawable_otp_input_selected)
                } else {
                    if (editText.text.isNotEmpty()) {
                        view.background = ContextCompat.getDrawable(requireContext(), R.drawable.drawable_otp_input_selected_grey)
                    } else {
                        view.background = ContextCompat.getDrawable(requireContext(), R.drawable.drawable_otp_input_unselected)
                    }
                }
            }
        }
    }
}