package com.bagzag.app.ui.dashboard.fragment

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagzag.app.data.pojo.Categories
import com.bagzag.app.databinding.DashboardFragmentCategoriesDetailsBinding
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.dashboard.adapter.AdapterCategoriesDetails

class CategoriesDetailsFragment : BaseFragment<DashboardFragmentCategoriesDetailsBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): DashboardFragmentCategoriesDetailsBinding {
        return DashboardFragmentCategoriesDetailsBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        var categories: Categories? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            categories = arguments?.getParcelable("category", Categories::class.java)
        } else {
            categories = arguments?.getParcelable("category")
        }

        if (categories != null) {
            binding.categoryName.text = categories.categoriesName
            binding.categoryImage.setImageResource(categories.categoriesImage)
        }

        val categoriesDetailsList = mutableListOf<String>().apply {
            add("Top Wear")
            add("Watches")
            add("Footwear")
            add("Inner Wear")
            add("Ethnic Wear")
            add("Sleepwear")
            add("Jeans")
            add("Inner Wear")
            add("Ethnic Wear")
            add("Sleepwear")
            add("Jeans")
        }

        val adapter = AdapterCategoriesDetails()
        binding.categoriesDetailsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.categoriesDetailsRecyclerView.adapter = adapter
        adapter.setCategoriesDetailsList(categoriesDetailsList)
    }
}