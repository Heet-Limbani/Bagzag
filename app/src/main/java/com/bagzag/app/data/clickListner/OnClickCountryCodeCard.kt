package com.bagzag.app.data.clickListner

import android.view.View
import com.bagzag.app.data.pojo.CountryCode

interface OnClickCountryCodeCard {

    fun onClick(countryCode: CountryCode, position: Int, view: View)

}