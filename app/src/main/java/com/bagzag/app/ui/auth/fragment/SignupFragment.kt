package com.bagzag.app.ui.auth.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bagzag.app.R
import com.bagzag.app.core.Session
import com.bagzag.app.data.clickListner.OnClickCountryCodeCard
import com.bagzag.app.data.pojo.CountryCode
import com.bagzag.app.data.pojo.request.SignupRequest
import com.bagzag.app.databinding.AuthFragmentSignupBinding
import com.bagzag.app.exception.ApplicationException
import com.bagzag.app.ui.activity.DashboardActivity
import com.bagzag.app.ui.auth.viewmodel.LoginViewModel
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class SignupFragment : BaseFragment<AuthFragmentSignupBinding>() {
    var selectedPosition = 0
    var selectedCountryCode = "+1"
    private val loginViewModel: LoginViewModel by viewModels()
    private var deviceType = "A"
    private var loginType = "S"

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var session: Session

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.firstNameInput)
                    .checkEmpty().errorMessage(getString(R.string.validation_enter_first_name))
                    .checkMinDigits(3).errorMessage(getString(R.string.validation_valid_first_name))
                    .check()

                validator.submit(binding.lastNameInput)
                    .checkEmpty().errorMessage(getString(R.string.validation_enter_last_name))
                    .checkMinDigits(3).errorMessage(getString(R.string.validation_valid_last_name))
                    .check()

                validator.submit(binding.emailInput)
                    .checkEmpty().errorMessage(getString(R.string.validation_enter_email))
                    .checkValidEmail().errorMessage(getString(R.string.validation_valid_email))
                    .check()

                validator.submit(binding.mobileNumber)
                    .checkEmpty().errorMessage(getString(R.string.validation_enter_mobile_number))
                    .checkMinDigits(10).errorMessage(getString(R.string.validation_valid_mobile_number))
                    .checkMaxDigits(10).errorMessage(getString(R.string.validation_valid_mobile_number))
                    .check()

                validator.submit(binding.passwordInput)
                    .checkEmpty().errorMessage(getString(R.string.validation_enter_password))
                    .check()

                validator.submit(binding.confirmPasswordInput)
                    .checkEmpty().errorMessage(getString(R.string.validation_enter_confirm_password))
                    .matchString(binding.passwordInput.text.toString()).errorMessage(getString(R.string.validation_password_mismatch))
                    .check()

                if (!binding.termsRadio.isChecked) {
//                    binding.termsRadio.error = "Please accept the terms and conditions"
                    throw ApplicationException("Please accept the terms and conditions")
                }

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): AuthFragmentSignupBinding {
        return AuthFragmentSignupBinding.inflate(inflater, container, attachToRoot)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
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
                    selectedCountryCode = countryCode.countryCode
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

            if (isValid){
                callSignupApi(binding.firstNameInput.text.toString(),binding.lastNameInput.text.toString(),binding.emailInput.text.toString(),binding.countryCode.text.toString(),binding.mobileNumber.text.toString(),loginType,binding.passwordInput.text.toString(),session.deviceId,deviceType)
            }
        }
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun callSignupApi(firstName: String, lastName: String, email: String, countryCode: String, mobileNumber: String, loginType: String, password: String, deviceToken: String, deviceType: String) {

        val signupRequest = SignupRequest(
            firstName,
            lastName,
            email,
            countryCode,
            mobileNumber,
            loginType,
            password,
            password,
            deviceType
        )

        loginViewModel.signup(signupRequest)
    }

    private fun observeLiveData() {
        loginViewModel.signUpLiveData.observe(this, { responseBody ->

            val code = responseBody.responseCode
            Log.d("SignupFragment", "Data: $code")
            if (code == 1) {
                Toast.makeText(
                    requireContext(),
                    "Signup Successful, Code: $code",
                    Toast.LENGTH_LONG
                ).show()

                val cleanedNumber = binding.mobileNumber.text?.filter { it.isDigit() }

                var mobileNumber = ""

                if (cleanedNumber != null) {

                    val formattedNumber = when (cleanedNumber.length) {
                        10 -> "${cleanedNumber.substring(0, 3)}-${
                            cleanedNumber.substring(
                                3,
                                6
                            )
                        }-${cleanedNumber.substring(6)}"

                        else -> cleanedNumber
                    }

                    mobileNumber = "$selectedCountryCode-$formattedNumber"
                }

                val bundle = Bundle()
                bundle.putString("mobileNumber", mobileNumber)
                bundle.putString("nextPage", "DashboardActivity")
                navigator.load(PhoneVerificationFragment::class.java).setBundle(bundle).replace(true,"PhoneVerificationFragment")

            } else {
                Toast.makeText(
                    requireContext(),
                    "Signup Unsuccessful, Code: $code", Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
