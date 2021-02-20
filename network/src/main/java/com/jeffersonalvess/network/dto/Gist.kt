package com.jeffersonalvess.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Gist (
    val id: String,

    val description: String,

    val html_url: String,

    val owner: GistOwner,

    @Expose
    @SerializedName("files")
    val files: Map<String, Files>
)
