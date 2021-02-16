package com.jeffersonalvess.network.dto

data class Gist (
    val id: String,
    val description: String,
    val html_url: String,
    val owner: GistOwner,
    val files: GistFile
)
