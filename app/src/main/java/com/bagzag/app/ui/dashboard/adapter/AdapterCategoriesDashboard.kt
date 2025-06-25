package com.bagzag.app.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagzag.app.data.pojo.Categories
import com.bagzag.app.databinding.ViewCategoriesCardBinding

class AdapterCategoriesDashboard:RecyclerView.Adapter<AdapterCategoriesDashboard.CategoryViewHolder>() {

    val categoriesList = mutableListOf<Categories>()

    fun setCategories(categoriesList: MutableList<Categories>){
        this.categoriesList.clear()
        this.categoriesList.addAll(categoriesList)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val rootView:ViewCategoriesCardBinding):RecyclerView.ViewHolder(rootView.root){
        fun setData(categories: Categories){
            rootView.categoryImage.setImageResource(categories.categoriesImage)
            rootView.categoryName.text = categories.categoriesName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ViewCategoriesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.setData(categoriesList[position])
    }
}