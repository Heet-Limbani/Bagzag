package com.bagzag.app.ui.auth.fragment

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bagzag.app.R
import com.bagzag.app.databinding.AuthFragmentWelcomeThreeBinding
import com.bagzag.app.ui.base.BaseFragment

class WelcomeThreeFragment:BaseFragment<AuthFragmentWelcomeThreeBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentWelcomeThreeBinding {
        return AuthFragmentWelcomeThreeBinding.inflate(inflater,container,attachToRoot)
    }

    override fun bindData() {
        val normalText = getString(R.string.experience_the_realtime)
        val pinkText = getString(R.string.tracking)
        val space = " "

        val spannableString = SpannableString(normalText + space + pinkText)

        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.pink)),
            (normalText + space).length,
            (normalText + space + pinkText).length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.trackDescription.text = spannableString
    }
}