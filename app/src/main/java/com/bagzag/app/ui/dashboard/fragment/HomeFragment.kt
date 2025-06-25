package com.bagzag.app.ui.dashboard.fragment

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagzag.app.R
import com.bagzag.app.data.pojo.Categories
import com.bagzag.app.data.pojo.ProductCard
import com.bagzag.app.databinding.DashboardFragmentHomeBinding
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.dashboard.adapter.AdapterCategoriesDashboard
import com.bagzag.app.ui.dashboard.adapter.AdapterProductCard
import com.bagzag.app.ui.dashboard.adapter.AdapterProductCardHorizontal

class HomeFragment: BaseFragment<DashboardFragmentHomeBinding>(){
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): DashboardFragmentHomeBinding {
        return DashboardFragmentHomeBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        val normalText = getString(R.string.hi_)
        val boldText = getString(R.string.david)
        val space = " "

        val spannableString = SpannableString(normalText + space + boldText)

        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            (normalText + space).length,
            (normalText + space + boldText).length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.heading.text = spannableString

        val categoriesList = mutableListOf<Categories>().apply {
            add(Categories(R.drawable.ic_men,"Men"))
            add(Categories(R.drawable.ic_women,"Women"))
            add(Categories(R.drawable.ic_kids,"Kids"))
            add(Categories(R.drawable.ic_beauty,"Beauty"))
        }

        val adapterCategories = AdapterCategoriesDashboard()
        binding.recyclerViewCategories.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerViewCategories.adapter = adapterCategories
        adapterCategories.setCategories(categoriesList)

        val productCardList = mutableListOf<ProductCard>().apply {
            add(ProductCard(R.drawable.ic_shirt,"Loose Textured T-Shirt","30% Off",false,4.5,256,"Free Shipping",159.99,119.99))
            add(ProductCard(R.drawable.ic_knit_sleeve,"Loose Knit 3/4 Sleeve",null,true,4.5,256,"Bestseller",159.99,119.99))
            add(ProductCard(R.drawable.ic_shirt,"Loose Textured T-Shirt",null,true,4.5,256,null,159.99,119.99))
            add(ProductCard(R.drawable.ic_knit_sleeve,"Loose Knit 3/4 Sleeve",null,true,4.5,256,"Bestseller",159.99,119.99))
        }

        val adapterProductCard = AdapterProductCard()
        binding.recyclerViewProductBestseller.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerViewProductBestseller.adapter = adapterProductCard
        adapterProductCard.setProductCardList(productCardList)

        val adapterRecentProduct = AdapterProductCard()
        binding.recyclerViewRecentlyViewed.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
        binding.recyclerViewRecentlyViewed.adapter = adapterRecentProduct
        adapterRecentProduct.setProductCardList(productCardList)

        val productCardListHorizontal = mutableListOf<ProductCard>().apply {
            add(ProductCard(R.drawable.ic_shirt,"Loose Textured T-Shirt",null,false,4.5,256,"Free Shipping",159.99,119.99))
            add(ProductCard(R.drawable.ic_shirt,"Loose Knit 3/4 Sleeve",null,true,4.5,256,"Bestseller",159.99,119.99))
            add(ProductCard(R.drawable.ic_shirt,"Loose Textured T-Shirt",null,true,4.5,256,null,159.99,119.99))
            add(ProductCard(R.drawable.ic_shirt,"Loose Knit 3/4 Sleeve",null,true,4.5,256,"Bestseller",159.99,119.99))
        }

        val adapterProductCardHorizontal = AdapterProductCardHorizontal()
        binding.recyclerViewBuyerMostLoved.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerViewBuyerMostLoved.adapter = adapterProductCardHorizontal
        adapterProductCardHorizontal.setProductCardList(productCardListHorizontal)

        val adapterProductCardFindThings = AdapterProductCard()
        binding.recyclerViewFindThings.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerViewFindThings.adapter = adapterProductCardFindThings
        adapterProductCardFindThings.setProductCardList(productCardList)
    }
}
