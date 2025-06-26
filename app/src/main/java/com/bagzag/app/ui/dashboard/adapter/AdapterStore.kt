package com.bagzag.app.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagzag.app.data.pojo.StoreCard
import com.bagzag.app.databinding.ViewStoreCardBinding

class AdapterStore : RecyclerView.Adapter<AdapterStore.StoreViewHolder>() {

    val storeList = mutableListOf<StoreCard>()

    fun setStoreList(storeList: List<StoreCard>) {
        this.storeList.clear()
        this.storeList.addAll(storeList)
        notifyDataSetChanged()
    }

    inner class StoreViewHolder(val rootView: ViewStoreCardBinding) :
        RecyclerView.ViewHolder(rootView.root) {
        fun setStoreCard(storeCard: StoreCard) {
            rootView.storeImage.setImageResource(storeCard.storeImage)
            rootView.storeName.text = storeCard.storeName
            rootView.storeAddress.text = storeCard.storeAddress
            if (storeCard.favorites) {
                rootView.storeLike.setImageResource(com.bagzag.app.R.drawable.ic_liked_grey)
            } else {
                rootView.storeLike.setImageResource(com.bagzag.app.R.drawable.ic_unliked)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            ViewStoreCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.setStoreCard(storeList[position])
    }
}