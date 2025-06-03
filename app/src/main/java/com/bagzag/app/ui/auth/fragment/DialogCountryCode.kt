package com.bagzag.app.ui.auth.fragment

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagzag.app.data.clickListner.OnClickCountryCodeCard
import com.bagzag.app.data.pojo.CountryCode
import com.bagzag.app.databinding.ViewCountryCodeDialogBinding
import com.bagzag.app.ui.auth.adapter.AdapterCountryCodeList
import com.bagzag.app.ui.base.BaseDialogFragment

class DialogCountryCode:BaseDialogFragment<ViewCountryCodeDialogBinding>() {
    var selectedPosition = 0
    var onClickCountryCodeCard: OnClickCountryCodeCard? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): ViewCountryCodeDialogBinding {
        return ViewCountryCodeDialogBinding.inflate(inflater, container, attachToRoot)
    }

    override fun onStart() {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        super.onStart()
    }

    override fun bindData() {

        binding.closeButton.setOnClickListener {
            dismiss()
        }

        val countryCodeList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelableArrayList("countryCodeList",CountryCode::class.java)
        }
        else{
            arguments?.getParcelableArrayList<CountryCode>("countryCodeList")
        }

        val adapter = AdapterCountryCodeList()
        binding.recyclerViewCountryCode.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewCountryCode.adapter = adapter

        if (countryCodeList != null) {
            adapter.setCountryCodeList(countryCodeList)
        }

        adapter.selectedPosition = selectedPosition
        binding.recyclerViewCountryCode.smoothScrollToPosition(selectedPosition)

        adapter.setOnClickCountryCodeCardObject(object: OnClickCountryCodeCard{
            override fun onClick(countryCode: CountryCode, position: Int, view: View) {
                onClickCountryCodeCard?.onClick(countryCode, position, view)
                dismiss()
            }
        })
    }
}