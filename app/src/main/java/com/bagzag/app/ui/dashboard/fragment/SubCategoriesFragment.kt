package com.bagzag.app.ui.dashboard.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.bagzag.app.R
import com.bagzag.app.data.pojo.ProductCard
import com.bagzag.app.databinding.DashboardFragmentSubCategoriesDetailsBinding
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.dashboard.adapter.AdapterProductCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubCategoriesFragment : BaseFragment<DashboardFragmentSubCategoriesDetailsBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): DashboardFragmentSubCategoriesDetailsBinding {
        return DashboardFragmentSubCategoriesDetailsBinding.inflate(
            inflater,
            container,
            attachToRoot
        )
    }

    override fun bindData() {

        val productCardList = mutableListOf<ProductCard>().apply {
            add(ProductCard(R.drawable.ic_person_one,"Loose Textured T-Shirt","30% Off",false,4.5,256,"Free Shipping",159.99,119.99))
            add(ProductCard(R.drawable.ic_person_one,"Loose Knit 3/4 Sleeve",null,true,4.5,256,"Bestseller",159.99,119.99))
            add(ProductCard(R.drawable.ic_person_one,"Loose Textured T-Shirt",null,true,4.5,256,null,159.99,119.99))
            add(ProductCard(R.drawable.ic_person_one,"Loose Knit 3/4 Sleeve",null,true,4.5,256,"Bestseller",159.99,119.99))
            add(ProductCard(R.drawable.ic_person_one,"Loose Textured T-Shirt","30% Off",false,4.5,256,"Free Shipping",159.99,119.99))
            add(ProductCard(R.drawable.ic_person_one,"Loose Knit 3/4 Sleeve",null,true,4.5,256,"Bestseller",159.99,119.99))
            add(ProductCard(R.drawable.ic_person_one,"Loose Textured T-Shirt",null,true,4.5,256,null,159.99,119.99))
            add(ProductCard(R.drawable.ic_person_one,"Loose Knit 3/4 Sleeve",null,true,4.5,256,"Bestseller",159.99,119.99))
        }

        val adapterRecentProduct = AdapterProductCard()
        binding.recyclerSubCategoryProduct.layoutManager = GridLayoutManager(requireContext(),2,
            GridLayoutManager.VERTICAL,false)
        binding.recyclerSubCategoryProduct.adapter = adapterRecentProduct
        adapterRecentProduct.setProductCardList(productCardList)

        val subCategoryName = arguments?.getString("subCategoryName")

        binding.heading.text = subCategoryName

        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.sort.setOnClickListener{

            val sortBottomSheet = SortBottomSheetFragment()
//            sortBottomSheet.show(requireActivity().supportFragmentManager, "SortBottomSheet")
            sortBottomSheet.show(childFragmentManager, SortBottomSheetFragment::class.java.simpleName)
        }
    }
}