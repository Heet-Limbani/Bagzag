package com.bagzag.app.ui.dashboard.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagzag.app.R
import com.bagzag.app.data.pojo.StoreCard
import com.bagzag.app.databinding.DashboardFragmentFavoritesStoreBinding
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.dashboard.adapter.AdapterStore

class FavoritesStoreFragment:BaseFragment<DashboardFragmentFavoritesStoreBinding>()

{
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): DashboardFragmentFavoritesStoreBinding {
        return DashboardFragmentFavoritesStoreBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        val storeList = mutableListOf<StoreCard>().apply {
            add(StoreCard(R.drawable.ic_store_one,"The New Store",getString(R.string._3038_godfrey_street_tigard_or_97223),true))
            add(StoreCard(R.drawable.ic_store_two,"The New Store",getString(R.string._3038_godfrey_street_tigard_or_97223),true))
            add(StoreCard(R.drawable.ic_store_one,"The New Store",getString(R.string._3038_godfrey_street_tigard_or_97223),true))
            add(StoreCard(R.drawable.ic_store_two,"The New Store",getString(R.string._3038_godfrey_street_tigard_or_97223),true))
            add(StoreCard(R.drawable.ic_store_one,"The New Store",getString(R.string._3038_godfrey_street_tigard_or_97223),true))
            add(StoreCard(R.drawable.ic_store_two,"The New Store",getString(R.string._3038_godfrey_street_tigard_or_97223),true))
            add(StoreCard(R.drawable.ic_store_one,"The New Store",getString(R.string._3038_godfrey_street_tigard_or_97223),true))
        }

        val adapter = AdapterStore()
        binding.recyclerFavoritesStore.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.recyclerFavoritesStore.adapter = adapter
        adapter.setStoreList(storeList)
    }
}