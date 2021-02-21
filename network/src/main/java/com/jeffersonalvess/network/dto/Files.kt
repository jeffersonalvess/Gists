package com.jeffersonalvess.network.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Files (
    @SerializedName("filename")
    val name: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("language")
    val language: String,

    @SerializedName("raw_url")
    val url: String
)  : Parcelable
