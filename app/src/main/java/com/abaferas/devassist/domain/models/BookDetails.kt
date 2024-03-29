package com.abaferas.devassist.domain.models

import com.abaferas.devassist.data.model.books.Pdf
import com.google.gson.annotations.SerializedName

data class BookDetails(
    val error: String,
    val title: String,
    val subtitle: String,
    val authors: String,
    val publisher: String,
    val isbn10: String,
    val isbn13: String,
    val pages: String,
    val year: String,
    val rating: String,
    val desc: String,
    val price: String,
    val image: String,
    val url: String,
    val pdf: Pdf? = Pdf()
)
