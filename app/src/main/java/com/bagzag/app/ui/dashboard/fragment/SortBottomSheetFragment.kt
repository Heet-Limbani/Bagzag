package com.bagzag.app.ui.dashboard.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bagzag.app.R
import com.bagzag.app.databinding.BottomSheetSortFragmentBinding
import com.bagzag.app.ui.base.BaseBottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.shape.ShapeAppearanceModel

class SortBottomSheetFragment : BaseBottomSheetDialogFragment<BottomSheetSortFragmentBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean
    ): BottomSheetSortFragmentBinding {
        return BottomSheetSortFragmentBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        val recentProductList = mutableListOf<String>().apply {
                add(resources.getString(R.string.newest_oldest))
            add(resources.getString(R.string.oldest_newest))
        }

        val ratingProductList = mutableListOf<String>().apply {
            add(resources.getString(R.string.high_low))
            add(resources.getString(R.string.low_high))
        }

        for (i in 0 until recentProductList.size) {
            binding.recentProductChipGroup.addView(getChip(recentProductList[i]))
        }

        for (i in 0 until ratingProductList.size) {
            binding.ratingChipGroup.addView(getChip(ratingProductList[i]))
            binding.priceChipGroup.addView(getChip(ratingProductList[i]))
            binding.offerChipGroup.addView(getChip(ratingProductList[i]))
        }
    }

    private fun getChip(value: String): Chip {
        val chip = Chip(requireContext()).apply {
            text = value
            isCheckable = true
            isClickable = true
            isCheckedIconVisible = false
            isFocusable = true

            // Styling
            chipBackgroundColor = ContextCompat.getColorStateList(context, R.color.transparent)
            chipStrokeWidth = resources.getDimension(com.intuit.sdp.R.dimen._1sdp)
            chipStrokeColor = ContextCompat.getColorStateList(
                context, R.color.sort_chip_selection_color_black_grey
            )
            chipStartPadding = resources.getDimension(com.intuit.sdp.R.dimen._5sdp)
            chipEndPadding = resources.getDimension(com.intuit.sdp.R.dimen._5sdp)

            // Text
            setTextColor(
                ContextCompat.getColorStateList(
                    context,
                    R.color.sort_chip_selection_color_black_grey
                )
            )
            textSize = resources.getDimension(com.intuit.ssp.R.dimen._4ssp)
            typeface = ResourcesCompat.getFont(context, R.font.inter_regular)
            maxLines = 1

            // Corner radius
            shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
                .setAllCornerSizes(resources.getDimension(com.intuit.sdp.R.dimen._2sdp)).build()

            // Layout height
            layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._35sdp)
            )
        }

        return chip
    }
}
