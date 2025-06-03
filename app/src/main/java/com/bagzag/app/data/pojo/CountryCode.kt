package com.bagzag.app.data.pojo

import android.os.Parcel
import android.os.Parcelable

data class CountryCode(val countryFlag:Int ,val countryCode:String, val countryName: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(countryFlag)
        parcel.writeString(countryCode)
        parcel.writeString(countryName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryCode> {
        override fun createFromParcel(parcel: Parcel): CountryCode {
            return CountryCode(parcel)
        }

        override fun newArray(size: Int): Array<CountryCode?> {
            return arrayOfNulls(size)
        }
    }
}
