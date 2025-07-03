package com.bagzag.app.ui.auth.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bagzag.app.databinding.ViewSuccessDialogBinding
import com.bagzag.app.ui.base.BaseDialogFragment
import com.bagzag.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogSuccess:BaseDialogFragment<ViewSuccessDialogBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): ViewSuccessDialogBinding {
        return ViewSuccessDialogBinding.inflate(inflater,container,attachToRoot)
    }

    override fun bindData() {
        val heading = arguments?.getString("heading")
        val subheading = arguments?.getString("subHeading")
        val description = arguments?.getString("description")
        val buttonText = arguments?.getString("button")
        val className = arguments?.getString("navigation")

        binding.dialogHeading.text = heading
        binding.dialogDescription.text = description
        binding.okButton.text = buttonText
        binding.dialogDescription.text = subheading

        binding.okButton.setOnClickListener {
            try {
                val navigationClass = Class.forName(className!!)
                val fragment = navigationClass.getDeclaredConstructor().newInstance() as? BaseFragment<*>?: throw IllegalArgumentException("Passed class is not a BaseFragment")
                navigator.load(fragment::class.java).replace(true, fragment.toString())
                dismiss()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}