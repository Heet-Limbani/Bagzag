package com.bagzag.app.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagzag.app.databinding.ViewCategoriesDetailsBinding

class AdapterCategoriesDetails :
    RecyclerView.Adapter<AdapterCategoriesDetails.CategoriesDetailsViewHolder>() {

    val categoriesDetailsList = mutableListOf<String>()

    fun setCategoriesDetailsList(categoriesDetailsList: List<String>) {
        this.categoriesDetailsList.clear()
        this.categoriesDetailsList.addAll(categoriesDetailsList)
        notifyDataSetChanged()
    }

    inner class CategoriesDetailsViewHolder(val rootView: ViewCategoriesDetailsBinding) :
        RecyclerView.ViewHolder(rootView.root) {
        fun setData(subCategoryName: String) {
            rootView.subCategoryName.text = subCategoryName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesDetailsViewHolder {
        return CategoriesDetailsViewHolder(
            ViewCategoriesDetailsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }
    
    override fun getItemCount(): Int {
        return categoriesDetailsList.size
    }

    override fun onBindViewHolder(holder: CategoriesDetailsViewHolder, position: Int) {
        holder.setData(categoriesDetailsList[position])
    }
}

