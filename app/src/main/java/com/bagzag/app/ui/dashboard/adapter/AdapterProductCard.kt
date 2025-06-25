package com.bagzag.app.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagzag.app.R
import com.bagzag.app.data.pojo.ProductCard
import com.bagzag.app.databinding.ViewProductCardBinding

class AdapterProductCard : RecyclerView.Adapter<AdapterProductCard.ViewHolder>() {

    val productCardList = mutableListOf<ProductCard>()

    fun setProductCardList(list: List<ProductCard>) {
        productCardList.clear()
        productCardList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val rootView: ViewProductCardBinding) :
        RecyclerView.ViewHolder(rootView.root) {
        fun setData(productCard: ProductCard) {
            if (productCard.discount != null) {
                rootView.discount.text = productCard.discount.toString()
            } else {
                rootView.discount.visibility = View.GONE
            }

            if (productCard.wishList) {
                rootView.wishlist.setImageResource(R.drawable.ic_liked)
            }
            else {
                rootView.wishlist.setImageResource(R.drawable.ic_unliked)
            }

            rootView.productImage.setImageResource(productCard.productImage)
            rootView.productName.text = productCard.productName
            rootView.rating.text = productCard.rating.toString()
            val ratingCount = "| " + productCard.ratingCount.toString()
            rootView.ratingCount.text = ratingCount

            if (productCard.label != null) {
                if (productCard.label == "Free Shipping") {
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
            rootView.price.paintFlags = rootView.price.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
            rootView.price.text = price
            val finalPrice = "$ " + productCard.finalPrice.toString()
            rootView.finalPrice.text = finalPrice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewProductCardBinding.inflate(
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