package com.bagzag.app.ui.dashboard.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bagzag.app.databinding.DashboardFragmentFavoritesBinding
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.dashboard.adapter.AdapterFavoritesTabs
import com.google.android.material.tabs.TabLayoutMediator

class FavoritesFragment : BaseFragment<DashboardFragmentFavoritesBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): DashboardFragmentFavoritesBinding {
        return DashboardFragmentFavoritesBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        val adapter = AdapterFavoritesTabs(this)
        val tabTitles = listOf("Store", "Products")

        adapter.setPageCount(2)
        binding.viewPagerFavorites.adapter = adapter

        TabLayoutMediator(binding.tabLayoutFavorites, binding.viewPagerFavorites) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        binding.viewPagerFavorites.isUserInputEnabled = false
    }
}



