package com.abaferas.devassist.domain.models

import com.google.gson.annotations.SerializedName

data class Book(
    val title: String,
    val subtitle: String,
    val isbn13: String,
    val price: String,
    val image: String,
    val url: String,
)
