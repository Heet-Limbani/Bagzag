package com.bagzag.app.ui.dashboard.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bagzag.app.ui.dashboard.fragment.FavoritesFragment
import com.bagzag.app.ui.dashboard.fragment.FavoritesProductFragment
import com.bagzag.app.ui.dashboard.fragment.FavoritesStoreFragment

class AdapterFavoritesTabs(fragment: Fragment):FragmentStateAdapter(fragment) {

    private var pageCount: Int = 0

    fun setPageCount(pageCount: Int) {
        this.pageCount = pageCount
    }

    override fun getItemCount(): Int {
        return pageCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoritesStoreFragment()
            1 -> FavoritesProductFragment()
            else -> FavoritesStoreFragment()
        }
    }
}