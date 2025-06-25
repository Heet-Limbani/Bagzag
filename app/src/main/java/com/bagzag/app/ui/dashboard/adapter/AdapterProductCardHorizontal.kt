package com.bagzag.app.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagzag.app.R
import com.bagzag.app.data.pojo.ProductCard
import com.bagzag.app.databinding.ViewProductCardHorizontalBinding

class AdapterProductCardHorizontal :
    RecyclerView.Adapter<AdapterProductCardHorizontal.ViewHolder>() {

    val productCardList = mutableListOf<ProductCard>()

    fun setProductCardList(list: List<ProductCard>) {
        productCardList.clear()
        productCardList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val rootView: ViewProductCardHorizontalBinding) :
        RecyclerView.ViewHolder(rootView.root) {
        fun setData(productCard: ProductCard) {

            rootView.productImage.setImageResource(productCard.productImage)
            rootView.productName.text = productCard.productName
            val rating = productCard.rating.toString()
            rootView.rating.text = rating
            val ratingCount = "| " + productCard.ratingCount.toString()
            rootView.ratingCount.text = ratingCount

            if (productCard.label != null) {
                if (productCard.label == "Free Shipping") {
                    rootView.labelContainer.visibility = View.VISIBLE
                    val view = LayoutInflater.from(rootView.root.context)
                        .inflate(R.layout.view_free_shipping_card, rootView.labelContainer, false)
                    rootView.labelContainer.addView(view)
                } else if (productCard.label == "New Arrival") {
                    val view = LayoutInflater.from(rootView.root.context)
                        .inflate(R.layout.view_bestseller_card, rootView.labelContainer, false)
                    rootView.labelContainer.addView(view)
                }
            }

            val price = "$ " + productCard.price.toString()
            rootView.price.paintFlags =
                rootView.price.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
            rootView.price.text = price

            val finalPrice = "$ " + productCard.finalPrice.toString()
            rootView.finalPrice.text = finalPrice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewProductCardHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return productCardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(productCardList[position])
    }
}