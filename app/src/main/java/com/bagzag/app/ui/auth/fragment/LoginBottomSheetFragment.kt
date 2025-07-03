package com.bagzag.app.ui.auth.fragment

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bagzag.app.R
import com.bagzag.app.databinding.BottomSheetLoginFragmentBinding
import com.bagzag.app.ui.activity.DashboardActivity
import com.bagzag.app.ui.base.BaseBottomSheetDialogFragment
import com.bagzag.app.ui.dashboard.fragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginBottomSheetFragment:BaseBottomSheetDialogFragment<BottomSheetLoginFragmentBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): BottomSheetLoginFragmentBinding {
        return BottomSheetLoginFragmentBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        val normalText = getString(R.string.sign_in_to)
        val pinkText = getString(R.string.bagzag)
        val space = " "

        val spannableString = SpannableString(normalText + space + pinkText)

        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.pink)),
            (normalText + space).length,
            (normalText + space + pinkText).length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.signHeading.text = spannableString

        binding.forgotPassword.setOnClickListener {
            navigator.load(ForgotPasswordFragment::class.java).replace(true,"ForgotPasswordFragment")
            dismiss()
        }

        binding.signInButton.setOnClickListener {

            navigator.loadActivity(
                DashboardActivity::class.java,
                HomeFragment::class.java
            ).byFinishingCurrent().start()

            dismiss()
        }
    }
}





