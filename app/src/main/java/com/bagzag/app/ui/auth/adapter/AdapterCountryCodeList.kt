package com.bagzag.app.ui.auth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagzag.app.data.clickListner.OnClickCountryCodeCard
import com.bagzag.app.data.pojo.CountryCode
import com.bagzag.app.databinding.ViewCountryCodeCardBinding

class AdapterCountryCodeList:RecyclerView.Adapter<AdapterCountryCodeList.CountryCodeViewHolder>() {

    private val countryCodeList = mutableListOf<CountryCode>()

    private var onClickCountryCodeCard: OnClickCountryCodeCard? = null

    var selectedPosition = RecyclerView.NO_POSITION

    fun setOnClickCountryCodeCardObject(onClickCountryCodeCard: OnClickCountryCodeCard) {
        this.onClickCountryCodeCard = onClickCountryCodeCard
    }

    fun setCountryCodeList(countryCodeList: List<CountryCode>) {
        this.countryCodeList.clear()
        this.countryCodeList.addAll(countryCodeList)
        notifyDataSetChanged()
    }

    inner class CountryCodeViewHolder(private val rootView:ViewCountryCodeCardBinding):RecyclerView.ViewHolder(rootView.root),View.OnClickListener {

        init {
            rootView.countryCodeCard.setOnClickListener(this)
        }

        fun setData(countryCode: CountryCode, isSelected: Boolean) {

            rootView.flagImage.setImageResource(countryCode.countryFlag)
            rootView.countryName.text = countryCode.countryName
            rootView.countryCode.text = countryCode.countryCode

            if (isSelected) {
                rootView.countryCodeCard.setBackgroundResource(com.bagzag.app.R.color.grey)
            } else {
                rootView.countryCodeCard.setBackgroundResource(com.bagzag.app.R.color.white)
            }
        }

        override fun onClick(view: View) {
            onClickCountryCodeCard?.onClick(countryCodeList[layoutPosition],layoutPosition,view)
            val previousPosition = selectedPosition
            selectedPosition = layoutPosition
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryCodeViewHolder {
        return CountryCodeViewHolder(ViewCountryCodeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return countryCodeList.size
    }

    override fun onBindViewHolder(holder: CountryCodeViewHolder, position: Int) {
        holder.setData(countryCodeList[position], position == selectedPosition)
    }
}