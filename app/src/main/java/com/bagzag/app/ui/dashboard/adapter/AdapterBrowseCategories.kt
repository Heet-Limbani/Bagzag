package com.bagzag.app.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagzag.app.data.clickListner.OnClickBrowseCategories
import com.bagzag.app.data.pojo.Categories
import com.bagzag.app.databinding.ViewCategoriesCardBigBinding

class AdapterBrowseCategories:RecyclerView.Adapter<AdapterBrowseCategories.CategoryCardViewHolder>() {

    var onClickBrowseCategories: OnClickBrowseCategories? = null

    fun setObjectOnClickBrowseCategories(onClickBrowseCategories: OnClickBrowseCategories) {
        this.onClickBrowseCategories = onClickBrowseCategories
    }

    val cardList = mutableListOf<Categories>()

    fun setCategoriesCard(categories: List<Categories>) {
        cardList.clear()
        cardList.addAll(categories)
        notifyDataSetChanged()
    }

    inner class CategoryCardViewHolder(val rootView: ViewCategoriesCardBigBinding):RecyclerView.ViewHolder(rootView.root), View.OnClickListener {

        init {
            rootView.viewCategoriesCardBig.setOnClickListener(this)
        }

        fun setData(categories: Categories) {
            rootView.categoryImage.setImageResource(categories.categoriesImage)
            rootView.categoryName.text = categories.categoriesName
        }

        override fun onClick(view: View) {
            onClickBrowseCategories?.onClick(cardList[layoutPosition], layoutPosition, view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryCardViewHolder {
        return CategoryCardViewHolder(ViewCategoriesCardBigBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CategoryCardViewHolder, position: Int) {
        holder.setData(cardList[position])
    }
}


