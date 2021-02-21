package com.jeffersonalvess.network.dto

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gist (
    @SerializedName("id")
    val id: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("html_url")
    val url: String,

    @SerializedName("owner")
    val owner: GistOwner,

    @Expose
    @SerializedName("files")
    val files: Map<String, Files>
) : Parcelable