package com.bagzag.app.ui.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bagzag.app.R
import com.bagzag.app.data.clickListner.OnClickBrowseCategories
import com.bagzag.app.data.pojo.Categories
import com.bagzag.app.databinding.DashboardFrgamentBrowseCategoriesBinding
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.dashboard.adapter.AdapterBrowseCategories
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowseCategoriesFragment:BaseFragment<DashboardFrgamentBrowseCategoriesBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): DashboardFrgamentBrowseCategoriesBinding {
        return DashboardFrgamentBrowseCategoriesBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        val categoriesList = mutableListOf<Categories>().apply {
            add(Categories(R.drawable.ic_men,"Men"))
            add(Categories(R.drawable.ic_women,"Women"))
            add(Categories(R.drawable.ic_kids,"Kids"))
            add(Categories(R.drawable.ic_beauty,"Beauty"))
            add(Categories(R.drawable.ic_home_living,"Home & Living"))
            add(Categories(R.drawable.ic_gedgets,"Gedgets"))
            add(Categories(R.drawable.ic_toys_baby,"Toy & Baby"))
            add(Categories(R.drawable.ic_sports,"Sports"))
        }

        val adapter = AdapterBrowseCategories()
        binding.categoriesRecyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(), 2)
        binding.categoriesRecyclerView.adapter = adapter
        adapter.setCategoriesCard(categoriesList)

        adapter.setObjectOnClickBrowseCategories(object :OnClickBrowseCategories{
            override fun onClick(categories: Categories, position: Int, view: View) {
                val bundle = Bundle()
                bundle.putParcelable("category", categories)

                navigator.load(CategoriesDetailsFragment::class.java).setBundle(bundle).replace(true,"CategoriesDetailsFragment")
            }
        })
        binding.back.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}


