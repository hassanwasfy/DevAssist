package com.abaferas.devassist.data.model.books

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("subtitle")
    val subtitle: String? = "",
    @SerializedName("isbn13")
    val isbn13: String? = "",
    @SerializedName("price")
    val price: String? = "",
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("url")
    val url: String? = "",
)