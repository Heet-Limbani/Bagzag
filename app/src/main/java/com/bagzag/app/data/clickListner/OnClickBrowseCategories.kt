package com.bagzag.app.data.clickListner

import android.view.View
import com.bagzag.app.data.pojo.Categories

interface OnClickBrowseCategories {
    fun onClick(categories: Categories, position: Int, view: View)
}

