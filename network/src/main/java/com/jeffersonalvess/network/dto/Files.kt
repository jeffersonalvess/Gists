package com.jeffersonalvess.network.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Files (
    @SerializedName("type")
    val type: String
)  : Parcelable
