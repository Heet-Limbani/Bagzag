package com.bagzag.app.ui.auth.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bagzag.app.R
import com.bagzag.app.data.clickListner.OnClickCountryCodeCard
import com.bagzag.app.data.pojo.CountryCode
import com.bagzag.app.databinding.AuthFragmentSignupBinding
import com.bagzag.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<AuthFragmentSignupBinding>() {
    var selectedPosition = 0
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentSignupBinding {
        return AuthFragmentSignupBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
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

        val iAgreeTo = getString(R.string.i_agree_to)
        val termsAndConditionsClickable = getString(R.string.terms_condition)
        val and = getString(R.string.and)
        val privacyPolicyClickable = getString(R.string.privacy_policy)
        val space = " "

        val fullText =
            iAgreeTo + space + termsAndConditionsClickable + space + and + space + privacyPolicyClickable
        val spannableString = SpannableString(fullText)

        val termsStart = (iAgreeTo + space).length
        val termsEnd = termsStart + termsAndConditionsClickable.length

        val privacyStart = termsEnd + space.length + and.length + space.length
        val privacyEnd = privacyStart + privacyPolicyClickable.length

        val termsClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Handle Terms click
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(requireContext(), R.color.black)
                ds.isUnderlineText = false
            }
        }

        val privacyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Handle Privacy click
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(requireContext(), R.color.black)
                ds.isUnderlineText = true
            }
        }

        spannableString.setSpan(
            termsClickableSpan,
            termsStart,
            termsEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            privacyClickableSpan,
            privacyStart,
            privacyEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            termsStart,
            termsEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            privacyStart,
            privacyEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.termsText.text = spannableString

        binding.signupButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("nextPage","DashboardActivity")
            navigator.load(PhoneVerificationFragment::class.java).setBundle(bundle).replace(true,"PhoneVerificationFragment")
        }
    }
}
