package com.jeffersonalvess.network.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GistOwner (
    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatar: String
) : Parcelable
