package com.abaferas.devassist.data.model.books


import com.google.gson.annotations.SerializedName

data class Pdf(
    @SerializedName("Chapter 2")
    val chapter2: String? = "",
    @SerializedName("Chapter 5")
    val chapter5: String? = ""
)