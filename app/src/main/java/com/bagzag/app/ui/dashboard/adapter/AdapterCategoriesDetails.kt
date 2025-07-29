package com.bagzag.app.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagzag.app.data.clickListner.OnClickSubCategories
import com.bagzag.app.databinding.ViewCategoriesDetailsBinding

class AdapterCategoriesDetails :
    RecyclerView.Adapter<AdapterCategoriesDetails.CategoriesDetailsViewHolder>() {

    var onClickSubCategories: OnClickSubCategories? = null

    fun setObjectOnClickSubCategories(onClickSubCategories: OnClickSubCategories) {
        this.onClickSubCategories = onClickSubCategories
    }

    val categoriesDetailsList = mutableListOf<String>()

    fun setCategoriesDetailsList(categoriesDetailsList: List<String>) {
        this.categoriesDetailsList.clear()
        this.categoriesDetailsList.addAll(categoriesDetailsList)
        notifyDataSetChanged()
    }

    inner class CategoriesDetailsViewHolder(val rootView: ViewCategoriesDetailsBinding) :
        RecyclerView.ViewHolder(rootView.root), View.OnClickListener {

        init {
            rootView.subCategory.setOnClickListener(this)
        }

        fun setData(subCategoryName: String) {
            rootView.subCategoryName.text = subCategoryName
        }

        override fun onClick(view: View) {
            onClickSubCategories?.onClick(
                categoriesDetailsList[layoutPosition],
                layoutPosition,
                view
            )
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


