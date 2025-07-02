package com.bagzag.app.ui.dashboard.fragment

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bagzag.app.R
import com.bagzag.app.databinding.DashboardSearchFragmentBinding
import com.bagzag.app.ui.base.BaseFragment
import com.google.android.material.chip.Chip
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class SearchFragment : BaseFragment<DashboardSearchFragmentBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean
    ): DashboardSearchFragmentBinding {
        return DashboardSearchFragmentBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        val suggestions = listOf(
            "Apple",
            "Apricot",
            "Avocado",
            "Banana",
            "Blackberry",
            "Blueberry",
            "Cherry",
            "Coconut",
            "Date",
            "DragonFruit",
            "Grapes",
            "Guava",
            "Kiwi",
            "Lemon",
            "Mango",
            "Orange",
            "Peach",
            "Pear",
            "Pineapple",
            "Plum"
        )

        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_dropdown_item_1line, suggestions
        )

        binding.searchInput.setAdapter(adapter)

        binding.searchInput.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Hide recent searches when text is not empty
                val shouldShowRecent = s.isNullOrEmpty()
                if (shouldShowRecent) {
                    binding.recentSearchesHeading.visibility = View.VISIBLE
                    binding.recentSearchesChipGroup.visibility = View.VISIBLE
                } else {
                    binding.recentSearchesHeading.visibility = View.GONE
                    binding.recentSearchesChipGroup.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        binding.recentSearchesChipGroup.removeAllViews()

//        for (i in 0 until suggestions.size.coerceAtMost(5)) {
//            val chip = Chip(requireContext()).apply {
//                text = suggestions[i]
//                isCheckable = false
//                isClickable = true
//                setTextColor(ContextCompat.getColor(context, R.color.grey))
//                chipBackgroundColor = ContextCompat.getColorStateList(context, R.color.transparent)
//                chipStrokeWidth = resources.getDimension(com.intuit.sdp.R.dimen._1sdp)
//                chipStrokeColor = ContextCompat.getColorStateList(context, R.color.grey)
//                chipStartPadding = resources.getDimension(com.intuit.sdp.R.dimen._10sdp)
//                chipEndPadding = resources.getDimension(com.intuit.sdp.R.dimen._10sdp)
//                textSize = resources.getDimension(com.intuit.ssp.R.dimen._16ssp)
//                typeface = ResourcesCompat.getFont(context, R.font.inter_regular)
//                shapeAppearanceModel = ShapeAppearanceModel().withCornerSize(
//                    resources.getDimension(com.intuit.sdp.R.dimen._5sdp)
//                )
//            }
//            binding.recentSearchesChipGroup.addView(chip)
//        }

        for (i in 0 until suggestions.size.coerceAtMost(5)) {
            val chip = Chip(requireContext()).apply {
                text = suggestions[i]
                isCheckable = false
                isClickable = true
                isCheckedIconVisible = false

                // Styling
                chipBackgroundColor = ContextCompat.getColorStateList(context, R.color.transparent)
                chipStrokeWidth = resources.getDimension(com.intuit.sdp.R.dimen._1sdp)
                chipStrokeColor = ContextCompat.getColorStateList(context, R.color.grey)
                chipStartPadding = resources.getDimension(com.intuit.sdp.R.dimen._10sdp)
                chipEndPadding = resources.getDimension(com.intuit.sdp.R.dimen._10sdp)

                // Text
                setTextColor(ContextCompat.getColor(context, R.color.grey))
                textSize = resources.getDimension(com.intuit.ssp.R.dimen._6ssp)
                typeface = ResourcesCompat.getFont(context, R.font.inter_regular)
                maxLines = 1

                // Corner radius
                shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
                    .setAllCornerSizes(resources.getDimension(com.intuit.sdp.R.dimen._5sdp)).build()

                // Layout height
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._40sdp)
                )

                setOnClickListener {
                    // 1) put the chip text in the AutoComplete box
                    binding.searchInput.setText(text, /*filter*/ false)
                    // 2) move cursor to the end
                    binding.searchInput.setSelection(text.length)
                    // 3) show the drop‑down again (optional)
//                    binding.searchInput.showDropDown()
                    // 4) optionally hide “recent searches” section
                    binding.recentSearchesHeading.visibility = View.GONE
                    binding.recentSearchesChipGroup.visibility = View.GONE
                }

            }
            binding.recentSearchesChipGroup.addView(chip)
        }

        binding.searchInput.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val editText = view as MaterialAutoCompleteTextView
                val drawableEnd = editText.compoundDrawablesRelative[2] // Right drawable

                if (drawableEnd != null) {
                    // Get position where drawable starts
                    val drawableStartX =
                        editText.width - editText.paddingEnd - drawableEnd.intrinsicWidth

                    if (event.x >= drawableStartX) {
                        // Click is inside the drawable bounds
                        editText.text?.clear()
//                        editText.showDropDown() // Optional: show suggestions again
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }

    }
}